# Modernizing the examples to Struts 7.3.0

**Date:** 2026-08-14
**Status:** Approved design, ready for implementation planning

## Goal

Bring all 47 example modules up to the current Struts 7.3.0 standard: the
framework version itself, the XML DOCTYPEs and schemas the examples teach by
example, the `@StrutsParameter` annotations that parameter injection now
requires, and the project's own "Struts 2" naming.

## Background

Two findings from the survey reshaped this work, and both are worth recording
because they contradict the obvious assumptions.

**The annotation requirement is not new in 7.3.0.**
`struts.parameters.requireAnnotations=true` and
`struts.parameters.requireAnnotations.transitionMode=false` are already the
defaults in 7.2.1, the version the repo is on today. Any example missing an
annotation is therefore *already* silently dropping request parameters. The
bump does not introduce this class of bug; it is simply a good moment to fix
it.

**Only the action class needs annotating.** `ParametersInterceptor`
authorizes the *root* property of a parameter name against the action class
alone. Nested POJOs are not checked:

```java
// ParametersInterceptor.hasValidAnnotatedPropertyDescriptor
Method relevantMethod = paramDepth == 0 ? propDesc.getWriteMethod() : propDesc.getReadMethod();
...
if (paramDepth >= 1) {
    allowlistClass(propDesc.getPropertyType());
}
```

Two consequences follow. Model beans — `Person`, `State`, `MessageStore`,
`User` and the rest — need no annotations whatsoever, so the ~75 unannotated
classes with public setters are almost all irrelevant to this work. And a
property annotated at depth ≥ 1 has its type **auto-allowlisted**, which makes
much existing `struts.allowlist.packageNames` configuration redundant.

The same excerpt shows an asymmetry that is the most likely source of latent
bugs in the repo: **depth 0 reads the annotation from the setter, depth ≥ 1
reads it from the getter.** For a nested parameter such as
`personBean.firstName`, annotating `setPersonBean` has no effect — the
annotation must sit on `getPersonBean` with `depth=1`.

Everything genuinely new in 7.3.0 is backward-compatible by default:
`struts.multipart.maxFiles` / `maxParameterCount`, WebJars support
(`struts.webjars.enabled=true`), `struts.validators.skipValidatorsOnConversionError`,
i18n cache tuning, `struts.csp.report.maxSize`, and
`struts.locale.validateRequestLocale`. No new constants need to be set.

## Decisions

| Decision | Choice | Rationale |
|---|---|---|
| Struts DTD target | `struts-6.5.dtd` everywhere | Newest shipped; differs from 6.0 only by a `final` attribute on `<package>`, so parsing is unaffected. The DTD is teaching material in a tutorial repo. |
| `struts.allowlist.*` config | Leave untouched | Removing working security config is the riskiest possible edit here. Redundancy is harmless and arguably documents intent. Add only where the audit proves it missing. |
| `web.xml` schema | `web-app_6_0.xsd`, `version="6.0"` | Matches the 18 files already there; needs no container beyond current requirements. (`6_1` exists and matches the pinned servlet-api 6.1.0, but moves 47 files instead of 29 for no functional gain.) |
| `struts.chaining.requireAnnotations` | Leave at default `false` | Tightening runtime behaviour is out of place in a modernization PR. |
| Project naming | Rename "Struts 2" → "Apache Struts" | The project is Struts 7; the naming is stale. |
| Verification | Static audit + maintainer review | Approved by the maintainer. See Verification. |
| Delivery | One PR, one commit per concern, no JIRA prefix | Keeps the risky annotation commit reviewable apart from mechanical churn. |

## Scope

### 1. Version bump

`struts2.version` `7.2.1` → `7.3.0` in the parent `pom.xml`. All 47 modules
inherit it.

### 2. XML DOCTYPEs and schemas

| Target | Files | Change |
|---|---|---|
| Struts config | 54 | All DOCTYPEs → public ID `-//Apache Software Foundation//DTD Struts Configuration 6.5//EN`, system ID `https://struts.apache.org/dtds/struts-6.5.dtd`. Currently 41 on `struts-2.5.dtd`, 9 on `struts-6.0.dtd`, 3 on `struts-2.3.dtd`, 1 on `struts-2.0.dtd`. Of these, 8 still use an `http://` system ID. |
| Validator | 13 | Normalize the 7 on `1.0.2` up to `1.0.3`; all to `https://`. |
| Tiles | 7 | `unknown-handler`'s `tiles-config_2_0.dtd` → `3_0` (the plugin bundles only `3_0` on the classpath); all to `https://`. |
| Maven | 5 | `http://maven.apache.org/maven-v4_0_0.xsd` → `https://maven.apache.org/xsd/maven-4.0.0.xsd`, matching the other 42. |
| `web.xml` | 29 | To `web-app_6_0.xsd` / `version="6.0"`. Currently 1 on `web-app_2_3.dtd`, 19 on `2_4`, 3 on `2_5`, 1 on `3_0`, 5 on `xmlns.jcp.org/…3_1`. 18 already correct. |

### 3. `@StrutsParameter` audit

**Enumerate the action set** from four sources, not from a blanket `class=`
grep (which wrongly picks up `<bean>` and `<interceptor>` classes such as
`FlexJSONWriter` and `ExceptionHandlerInterceptor`):

- `class=` on `<action>` elements in `struts*.xml` (~35 classes)
- convention-plugin scanned classes in `annotations`, `rest-angular`,
  `text-provider`, and `unknown-handler`
- REST controllers in `rest-angular`
- JSON-plugin actions in `json` and `json-customize` — `ParameterAuthorizer`
  exists so the JSON and REST plugins enforce the same rules

**Establish what each action actually receives** from `name=` on `<s:*>` form
tags in its JSPs, query strings in JSPs and READMEs, `<param>` in
`struts.xml`, `<field name=>` in validation XML, and existing test code. The
root property is the segment before the first `.` or `[`; the required depth
is the count of periods and brackets in the longest parameter name sharing
that root.

**Handle explicitly:** `ModelDriven` and `Preparable` actions
(`preparable-interface`, `crud`), and `CookieInterceptor`, which enforces the
same annotation rules.

**Deliverable:** a per-module table — action class, parameters observed,
required depth, current annotation state, verdict (`ok` / `fix` / `n/a` /
`unverified`) — with evidence cited per row so any row can be spot-checked
without re-deriving it. Only `fix` rows produce code changes. Expect roughly
8–12, but the audit decides.

Where parameters cannot be traced statically — no JSP form, or parameters
arriving from JavaScript — the row is marked `unverified` and raised with the
maintainer. No annotation is guessed at.

### 4. Naming and docs

Rename "Struts 2" → "Apache Struts" across 45 occurrences (28 `pom.xml` files
plus READMEs), including the parent pom's `<name>` and `<description>`.
Rephrase where a literal substitution would read awkwardly, e.g. "the Struts 2
Getting Started tutorials" → "the Apache Struts Getting Started tutorials".

Root README fixes:

- The claim "There is a README file in each module" is false — 6 of 47 have
  one. Correct the sentence.
- `[Releases](releases)` is a broken relative link. Point it at the real
  releases page.
- `http://` → `https://` on the license badge, `img.shields.io`, and the two
  `http://struts.apache.org` references.

Apply the same https pass to the 6 module READMEs.

## Out of scope

- **Writing the 41 missing module READMEs.** Useful for a tutorial repo, but
  authoring 41 documents is not modernization; it deserves its own task.
- **A WebJars example module.** WebJars is 7.3.0's headline addition and would
  make a good example, but designing, documenting, and maintaining a new
  module is separate work. Better as a follow-up PR once this lands.
- **Adding test infrastructure.** Considered and declined: per-module smoke
  tests would touch ~40 modules and clutter examples meant to teach one
  concept each.

## Delivery

One PR on a branch off `main`, four commits, no `WW-` prefix (matching the
repo's maintenance-commit convention):

1. `Bump Struts to 7.3.0`
2. `Modernize XML DOCTYPEs and schemas` — large, purely mechanical
3. `Fix @StrutsParameter annotations on action classes` — small, carries all
   the real risk, deliberately isolated from commit 2's churn
4. `Rename to Apache Struts and refresh README links`

## Verification

`mvn -B -V clean test` on Java 17 and 21, matching CI, after each commit. This
proves compilation and the 6 existing tests — nothing about runtime parameter
binding.

Beyond that:

- The audit table plus maintainer review is the agreed correctness mechanism.
- Run the `dependency-check` profile once, to confirm 7.3.0 introduces no new
  CVE.
- Spot-boot `form-tags` and `crud` under the Jetty plugin to confirm nested
  parameter binding works end to end — two representative modules, not all 47.

Two framework behaviours are useful cross-checks on the static audit:
`ParametersInterceptor` calls `notifyDeveloperOfError` when `devMode` is on,
so rejections are reported loudly rather than silently; and
`struts.parameters.requireAnnotations.transitionMode` downgrades rejection to
a warning.

## Risks

| Risk | Mitigation |
|---|---|
| An action whose parameters cannot be traced statically | Mark `unverified`, raise with the maintainer, change nothing |
| Compile-only CI cannot catch a wrong annotation | Isolated commit 3 + audit table + targeted Jetty spot-checks |
| Commit 2's churn hides a real change | Concern-per-commit split keeps mechanical and behavioural changes apart |
| Naming rename touches 45 user-visible strings | Mechanical and reviewable in its own commit; no code identifiers affected |
