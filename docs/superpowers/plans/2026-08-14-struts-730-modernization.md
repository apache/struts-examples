# Struts 7.3.0 Modernization Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Bring all 47 example modules to the Struts 7.3.0 standard — framework version, XML DOCTYPEs and schemas, `@StrutsParameter` annotations, and "Apache Struts" naming.

**Architecture:** Four independent sweeps over an existing Maven multi-module repo. Three are mechanical text transformations verified by exact match counts; one (the annotation fixes) is eight hand-verified source edits. No new modules, no new dependencies, no new test infrastructure.

**Tech Stack:** Java 17/21, Maven, Apache Struts 7.3.0, Jakarta Servlet 6.1, JSP, Log4j2.

**Spec:** `docs/superpowers/specs/2026-08-14-struts-730-modernization-design.md`

## Global Constraints

- Target Struts version: **7.3.0** (`struts2.version` in the parent `pom.xml`).
- Struts config DTD: public ID `-//Apache Software Foundation//DTD Struts Configuration 6.5//EN`, system ID `https://struts.apache.org/dtds/struts-6.5.dtd`.
- Validator DTD: public ID `-//Apache Struts//XWork Validator 1.0.3//EN`, system ID `https://struts.apache.org/dtds/xwork-validator-1.0.3.dtd`.
- Tiles DTD: public ID `-//Apache Software Foundation//DTD Tiles Configuration 3.0//EN`, system ID `https://tiles.apache.org/dtds/tiles-config_3_0.dtd`.
- Maven POM schema: `https://maven.apache.org/xsd/maven-4.0.0.xsd`.
- `web.xml` schema: `https://jakarta.ee/xml/ns/jakartaee/web-app_6_0.xsd`, `version="6.0"`.
- **Do not modify any `struts.allowlist.*` constant.** Spec decision: removing working security config is out of scope.
- **Do not change `struts.chaining.requireAnnotations`.** Leave at its default `false`.
- Do not add test dependencies or new test files. Spec decision.
- Branch: `feat/struts-730-modernization` (already checked out).
- Commit messages carry no `WW-` prefix.
- Every commit ends with the trailer `Co-Authored-By: Claude Opus 5 <noreply@anthropic.com>`.

**Note on TDD:** This plan does not follow a red-green cycle. The spec explicitly declined new test infrastructure, and the repo has only 6 test files, none covering the touched code. Each task therefore substitutes a **deterministic verification command with an expected exact count** for the failing-test step. Do not skip these — they are the only automated check that a sweep hit its intended targets and nothing else.

---

### Task 1: Bump Struts to 7.3.0

**Files:**
- Modify: `pom.xml:56`

**Interfaces:**
- Consumes: nothing
- Produces: `struts2.version` = `7.3.0`, inherited by all 47 modules

- [ ] **Step 1: Confirm the current version before changing it**

Run:
```bash
grep -n '<struts2.version>' pom.xml
```
Expected: exactly one line — `<struts2.version>7.2.1</struts2.version>`

- [ ] **Step 2: Apply the bump**

In `pom.xml`, replace:
```xml
<struts2.version>7.2.1</struts2.version>
```
with:
```xml
<struts2.version>7.3.0</struts2.version>
```

- [ ] **Step 3: Verify the whole reactor resolves and compiles against 7.3.0**

Run:
```bash
./mvnw -B -q clean test
```
Expected: BUILD SUCCESS. All 6 existing tests pass. If any module fails to resolve `7.3.0`, stop — do not proceed to Task 2.

- [ ] **Step 4: Confirm no other file pins a Struts version**

Run:
```bash
grep -rn '7\.2\.1' --include='pom.xml' . | grep -v target
```
Expected: no output. Module POMs all use `${struts2.version}`.

- [ ] **Step 5: Commit**

```bash
git add pom.xml
git commit -m "Bump Struts to 7.3.0

Co-Authored-By: Claude Opus 5 <noreply@anthropic.com>"
```

---

### Task 2: Normalize Struts, validator, tiles, and Maven DOCTYPEs

Pure find-and-replace across 79 files. The public ID and system ID are replaced independently so the transformation works regardless of how the DOCTYPE is wrapped across lines.

**Files:**
- Modify: 54 files containing `DOCTYPE struts`
- Modify: 13 files containing `DOCTYPE validators`
- Modify: 7 files containing `DOCTYPE tiles`
- Modify: 5 `pom.xml` files referencing `maven-v4_0_0.xsd`

**Interfaces:**
- Consumes: Task 1's version bump (7.3.0 ships `struts-6.5.dtd`)
- Produces: nothing consumed by later tasks

- [ ] **Step 1: Record the "before" counts**

Run:
```bash
grep -rlE '\-//Apache Software Foundation//DTD Struts Configuration [0-9.]+//EN' --include='*.xml' . | grep -v target | wc -l
grep -rlE '\-//Apache Struts//XWork Validator 1\.0\.[23]//EN' --include='*.xml' . | grep -v target | wc -l
grep -rlE '\-//Apache Software Foundation//DTD Tiles Configuration [0-9.]+//EN' --include='*.xml' . | grep -v target | wc -l
grep -rl 'maven-v4_0_0.xsd' --include='pom.xml' . | grep -v target | wc -l
```
Expected, in order: `54`, `13`, `7`, `5`

- [ ] **Step 2: Rewrite the Struts config DOCTYPEs**

```bash
grep -rlE '\-//Apache Software Foundation//DTD Struts Configuration [0-9.]+//EN' --include='*.xml' . | grep -v target | \
  xargs perl -pi -e '
    s{-//Apache Software Foundation//DTD Struts Configuration [0-9.]+//EN}{-//Apache Software Foundation//DTD Struts Configuration 6.5//EN}g;
    s{https?://struts\.apache\.org/dtds/struts-[0-9.]+\.dtd}{https://struts.apache.org/dtds/struts-6.5.dtd}g;
  '
```

- [ ] **Step 3: Rewrite the validator DOCTYPEs**

```bash
grep -rlE '\-//Apache Struts//XWork Validator 1\.0\.[23]//EN' --include='*.xml' . | grep -v target | \
  xargs perl -pi -e '
    s{-//Apache Struts//XWork Validator 1\.0\.[23]//EN}{-//Apache Struts//XWork Validator 1.0.3//EN}g;
    s{https?://struts\.apache\.org/dtds/xwork-validator-1\.0\.[23]\.dtd}{https://struts.apache.org/dtds/xwork-validator-1.0.3.dtd}g;
  '
```

- [ ] **Step 4: Rewrite the tiles DOCTYPEs**

```bash
grep -rlE '\-//Apache Software Foundation//DTD Tiles Configuration [0-9.]+//EN' --include='*.xml' . | grep -v target | \
  xargs perl -pi -e '
    s{-//Apache Software Foundation//DTD Tiles Configuration [0-9.]+//EN}{-//Apache Software Foundation//DTD Tiles Configuration 3.0//EN}g;
    s{https?://tiles\.apache\.org/dtds/tiles-config_[0-9_]+\.dtd}{https://tiles.apache.org/dtds/tiles-config_3_0.dtd}g;
  '
```

- [ ] **Step 5: Rewrite the Maven POM schema URLs**

```bash
grep -rl 'maven-v4_0_0.xsd' --include='pom.xml' . | grep -v target | \
  xargs perl -pi -e 's{http://maven\.apache\.org/maven-v4_0_0\.xsd}{https://maven.apache.org/xsd/maven-4.0.0.xsd}g'
```

- [ ] **Step 6: Verify every target was hit and nothing stale remains**

Do **not** use a `grep -P` negative lookahead here — combining `-E` and `-P` silently returns wrong counts rather than erroring, which would make this check lie. Match the stale versions explicitly instead:

```bash
echo "stale struts:    $(grep -rlE 'struts-(2\.0|2\.1|2\.1\.7|2\.3|2\.5|6\.0)\.dtd' --include='*.xml' . | grep -v target | wc -l | tr -d ' ')"
echo "new struts:      $(grep -rl 'struts-6.5.dtd' --include='*.xml' . | grep -v target | wc -l | tr -d ' ')"
echo "stale validator: $(grep -rl 'xwork-validator-1.0.2' --include='*.xml' . | grep -v target | wc -l | tr -d ' ')"
echo "new validator:   $(grep -rl 'xwork-validator-1.0.3.dtd' --include='*.xml' . | grep -v target | wc -l | tr -d ' ')"
echo "stale tiles:     $(grep -rl 'tiles-config_2_0' --include='*.xml' . | grep -v target | wc -l | tr -d ' ')"
echo "http dtds left:  $(grep -rlE 'http://(struts|tiles)\.apache\.org/dtds' --include='*.xml' . | grep -v target | wc -l | tr -d ' ')"
echo "stale maven:     $(grep -rl 'maven-v4_0_0.xsd' --include='pom.xml' . | grep -v target | wc -l | tr -d ' ')"
```
Expected after the rewrite: `stale struts: 0`, `new struts: 54`, `stale validator: 0`, `new validator: 13`, `stale tiles: 0`, `http dtds left: 0`, `stale maven: 0`

For reference, the same commands **before** the rewrite print `54`, `0`, `7`, `6`, `1`, `28`, `5`.

- [ ] **Step 7: Confirm every XML file is still well-formed**

```bash
find . -name '*.xml' -not -path '*/target/*' -print0 | xargs -0 -n1 xmllint --noout --nonet
```
Expected: no output. Any parse error means a replacement corrupted a file — fix before committing.

- [ ] **Step 8: Build**

```bash
./mvnw -B -q clean test
```
Expected: BUILD SUCCESS.

- [ ] **Step 9: Commit**

```bash
git add -A
git commit -m "Modernize XML DOCTYPEs to current Struts standards

Struts config DTDs to 6.5, XWork validator DTDs to 1.0.3, Tiles to 3.0,
and Maven POM schema URLs to the current location. All system IDs now
use https.

Co-Authored-By: Claude Opus 5 <noreply@anthropic.com>"
```

---

### Task 3: Migrate `web.xml` to the Jakarta EE 6.0 schema

29 of 47 `web.xml` files are stranded on `web-app_2_3.dtd` (1), `2_4` (19), `2_5` (3), `3_0` (1), and `xmlns.jcp.org/…3_1` (5). The other 18 are already correct and must not be touched. This needs a script rather than a regex because the root element's attributes must be rewritten as a unit, and the 2.3 file carries a DOCTYPE that has to be removed.

**Files:**
- Create: `/tmp/migrate_webxml.py` (throwaway; not committed)
- Modify: 29 `web.xml` files

**Interfaces:**
- Consumes: nothing
- Produces: nothing consumed by later tasks

- [ ] **Step 1: Record the "before" count**

```bash
grep -rlE 'web-app_(2_3\.dtd|2_4|2_5|3_0|3_1)' --include='web.xml' . | grep -v target | wc -l
```
Expected: `29`

- [ ] **Step 2: Write the migration script**

Create `/tmp/migrate_webxml.py`:

```python
import re, subprocess, sys

TARGET = (
    '<web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"\n'
    '         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"\n'
    '         xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee '
    'https://jakarta.ee/xml/ns/jakartaee/web-app_6_0.xsd"\n'
    '         version="6.0">'
)

files = subprocess.run(
    ["grep", "-rlE", r"web-app_(2_3\.dtd|2_4|2_5|3_0|3_1)", "--include=web.xml", "."],
    capture_output=True, text=True, check=True,
).stdout.split()
files = [f for f in files if "/target/" not in f]

changed = 0
for path in files:
    src = open(path, encoding="utf-8").read()
    orig = src
    # Drop any legacy DOCTYPE (the 2.3-era file).
    src = re.sub(r'<!DOCTYPE\s+web-app\b.*?>\s*', '', src, flags=re.S)
    # Replace the whole opening <web-app ...> tag, however its attributes wrap.
    src, n = re.subn(r'<web-app\b[^>]*?>', TARGET, src, count=1, flags=re.S)
    if n != 1:
        sys.exit(f"FAILED: no <web-app> opening tag found in {path}")
    # Preserve an ISO-8859-1 declaration by normalising to UTF-8 (content is ASCII).
    src = src.replace('<?xml version="1.0" encoding="ISO-8859-1"?>',
                      '<?xml version="1.0" encoding="UTF-8"?>')
    if src != orig:
        open(path, "w", encoding="utf-8").write(src)
        changed += 1

print(f"migrated {changed} files")
```

- [ ] **Step 3: Run it**

```bash
python3 /tmp/migrate_webxml.py
```
Expected: `migrated 29 files`

- [ ] **Step 4: Verify all 47 are now on the 6.0 schema and none was missed**

```bash
echo "on 6.0:     $(grep -rl 'web-app_6_0.xsd' --include='web.xml' . | grep -v target | wc -l)"
echo "stale left: $(grep -rlE 'java\.sun\.com|xmlns\.jcp\.org|web-app_(2_3|2_4|2_5|3_0|3_1)' --include='web.xml' . | grep -v target | wc -l)"
echo "total:      $(find . -name web.xml -not -path '*/target/*' | wc -l)"
```
Expected: `on 6.0: 47`, `stale left: 0`, `total: 47`

- [ ] **Step 5: Confirm well-formedness**

```bash
find . -name 'web.xml' -not -path '*/target/*' -print0 | xargs -0 -n1 xmllint --noout --nonet
```
Expected: no output.

- [ ] **Step 6: Confirm no filter/servlet declarations were lost**

The root-element rewrite must not have eaten body content. Compare against `HEAD`:

```bash
for f in $(git diff --name-only -- '*web.xml'); do
  a=$(git show HEAD:$f | grep -c '<filter-class>\|<servlet-class>\|<listener-class>')
  b=$(grep -c '<filter-class>\|<servlet-class>\|<listener-class>' $f)
  [ "$a" != "$b" ] && echo "MISMATCH $f: was $a now $b"
done; echo "comparison done"
```
Expected: `comparison done` with no `MISMATCH` lines.

- [ ] **Step 7: Build**

```bash
./mvnw -B -q clean test
```
Expected: BUILD SUCCESS.

- [ ] **Step 8: Commit**

```bash
git add -A
git commit -m "Migrate web.xml descriptors to Jakarta EE 6.0 schema

Brings the 29 descriptors still on Servlet 2.3-3.1 era schemas in line
with the 18 already using web-app_6_0.xsd.

Co-Authored-By: Claude Opus 5 <noreply@anthropic.com>"
```

---

### Task 4: Fix missing `@StrutsParameter` annotations

Eight fixes across five classes in four modules. Each was hand-verified against the submitting JSP form. **Do not add annotations beyond these eight** — the audit confirmed every other action is either correct, ModelDriven-exempt, or has a property that is never submitted (annotating those would wrongly widen the injection surface).

Rules being applied, from `ParametersInterceptor.hasValidAnnotatedPropertyDescriptor`:
- depth 0 → annotation goes on the **setter**
- depth ≥ 1 → annotation goes on the **getter**

**Files:**
- Modify: `crud/src/main/java/org/apache/struts/crud/action/PersonAction.java`
- Modify: `shiro-basic/src/main/java/org/apache/struts2/shiro/example/action/LoginAction.java`
- Modify: `shiro-basic/src/main/java/org/apache/struts2/shiro/example/action/WelcomeAction.java`
- Modify: `validation-messages/src/main/java/org/apache/struts/validation_messages/Login.java`
- Modify: `unknown-handler/src/main/java/org/apache/strutsexamples/actions/Login.java`

**Interfaces:**
- Consumes: Task 1's 7.3.0 bump
- Produces: nothing consumed by later tasks

- [ ] **Step 1: `crud` — annotate the nested `person` getter**

Evidence: `person.personId` and `person.country.countryId` are submitted, so depth is 2. `PersonAction` is `Preparable`, **not** `ModelDriven`, so it is not exempt.

Add the import alongside the existing `org.apache.struts2` imports:
```java
import org.apache.struts2.interceptor.parameter.StrutsParameter;
```

Replace:
```java
    public Person getPerson() {
        return person;
    }
```
with:
```java
    @StrutsParameter(depth = 2)
    public Person getPerson() {
        return person;
    }
```

Leave `setPerson` unannotated — at depth 2 the setter's annotation is never consulted.

- [ ] **Step 2: `shiro-basic/LoginAction` — annotate both setters**

Evidence: the login form submits `username` and `password` at depth 0. Note this file uses Allman braces; match that style.

Add the import:
```java
import org.apache.struts2.interceptor.parameter.StrutsParameter;
```

Replace:
```java
    public void setUsername(String username)
    {
        this.username = username;
    }
```
with:
```java
    @StrutsParameter
    public void setUsername(String username)
    {
        this.username = username;
    }
```

Replace:
```java
    public void setPassword(String password)
    {
        this.password = password;
    }
```
with:
```java
    @StrutsParameter
    public void setPassword(String password)
    {
        this.password = password;
    }
```

- [ ] **Step 3: `shiro-basic/WelcomeAction` — annotate the `username` setter**

Evidence: `execute()` compares the submitted `username` against the session attribute, so it genuinely expects the parameter. This file currently has no `org.apache.struts2` import.

Add after the existing imports:
```java
import org.apache.struts2.interceptor.parameter.StrutsParameter;
```

Replace:
```java
    public void setUsername(String username) 
    {
        this.username = username;
    }
```
with:
```java
    @StrutsParameter
    public void setUsername(String username) 
    {
        this.username = username;
    }
```

- [ ] **Step 4: `validation-messages/Login` — annotate both setters**

Evidence: `username` and `password` submitted at depth 0. The class extends `ExampleSupport`, which is a bare `ActionSupport` subclass declaring no members — nothing is inherited. The file currently has no imports at all.

Add after the `package` declaration:
```java
import org.apache.struts2.interceptor.parameter.StrutsParameter;
```

Replace:
```java
    public void setUsername(String username) {
        this.username = username;
    }
```
with:
```java
    @StrutsParameter
    public void setUsername(String username) {
        this.username = username;
    }
```

Replace:
```java
    public void setPassword(String password) {
        this.password = password;
    }
```
with:
```java
    @StrutsParameter
    public void setPassword(String password) {
        this.password = password;
    }
```

- [ ] **Step 5: `unknown-handler/Login` — annotate both setters**

Evidence: `WEB-INF/tiles/login.jsp` submits `<s:textfield name="email"/>` and `<s:password name="password"/>` to the `login-submit` action. This module is convention-plugin driven, which is why it is not in `struts.xml`.

Add alongside the existing `org.apache.struts2` imports:
```java
import org.apache.struts2.interceptor.parameter.StrutsParameter;
```

Replace:
```java
    public void setEmail(String email) {
        this.email = email;
    }
```
with:
```java
    @StrutsParameter
    public void setEmail(String email) {
        this.email = email;
    }
```

Replace:
```java
    public void setPassword(String password) {
        this.password = password;
    }
```
with:
```java
    @StrutsParameter
    public void setPassword(String password) {
        this.password = password;
    }
```

- [ ] **Step 6: Verify exactly 8 annotations were added across 5 files**

```bash
git diff --stat -- '*.java'
echo "added @StrutsParameter lines: $(git diff -U0 -- '*.java' | grep -c '^+.*@StrutsParameter')"
echo "added import lines:           $(git diff -U0 -- '*.java' | grep -c '^+import.*StrutsParameter')"
```
Expected: 5 files changed, `added @StrutsParameter lines: 8`, `added import lines: 5`

- [ ] **Step 7: Build**

```bash
./mvnw -B -q clean test
```
Expected: BUILD SUCCESS.

- [ ] **Step 8a: Runtime spot-check — `crud` (the module changed here)**

`PersonAction.prepare()` loads the person only when `person.getPersonId()` is non-null:

```java
if (person != null && person.getPersonId() != null) {
    person = personService.getPerson(person.getPersonId());
}
```

So the annotation is observable directly: with it, `person.personId=1` binds and the edit form renders the seeded record **Bruce Phillips** (`MemoryPersonDao` line 23); without it, `person` stays null, the load is skipped, and the form renders empty. The context path is `/crud` (`<contextPath>/${project.artifactId}</contextPath>`).

```bash
(cd crud && ../mvnw -q jetty:run) &
sleep 45
curl -s 'http://localhost:8080/crud/editPerson.action?person.personId=1' \
     -o /tmp/crud_edit.html -w 'HTTP %{http_code}\n'
echo "Bruce occurrences: $(grep -c 'Bruce' /tmp/crud_edit.html)"
kill %1
```
Expected: `HTTP 200` and `Bruce occurrences:` ≥ 1. **A count of 0 means the annotation is not taking effect — stop and investigate before committing.**

- [ ] **Step 8b: Runtime spot-check — `form-tags` (untouched control)**

`form-tags` was already correct (`@StrutsParameter(depth = 1)` on `getPersonBean`). This confirms Tasks 1–3 did not regress a module that previously worked. `thankyou.jsp` renders `<s:property value="personBean"/>`, and `Person.toString()` emits `First Name: <value>`.

```bash
(cd form-tags && ../mvnw -q jetty:run) &
sleep 45
curl -s -X POST 'http://localhost:8080/form-tags/save.action' \
     -d 'personBean.firstName=Ada&personBean.lastName=Lovelace' \
     -o /tmp/formtags_post.html -w 'HTTP %{http_code}\n'
echo "Ada occurrences: $(grep -c 'First Name: Ada' /tmp/formtags_post.html)"
kill %1
```
Expected: `HTTP 200` and `Ada occurrences:` ≥ 1.

- [ ] **Step 9: Commit**

```bash
git add -A
git commit -m "Add missing @StrutsParameter annotations to action classes

Eight parameters across crud, shiro-basic, unknown-handler and
validation-messages were silently dropped: struts.parameters.requireAnnotations
has defaulted to true since before 7.2.1, and these setters and getters
were never annotated.

Depth 0 parameters are annotated on the setter and nested parameters on
the getter, matching what ParametersInterceptor actually inspects.

Co-Authored-By: Claude Opus 5 <noreply@anthropic.com>"
```

---

### Task 5: Rename to Apache Struts and fix README claims

45 occurrences of "Struts 2" across 34 files (28 POMs, 6 READMEs). Blind substitution produces awkward phrasing, so the bulk replacement is followed by a manual read-through.

**Files:**
- Modify: 28 `pom.xml` files, 6 module `README.md` files, root `README.md`

**Interfaces:**
- Consumes: nothing
- Produces: nothing

- [ ] **Step 1: Record the "before" count**

```bash
grep -rn 'Struts 2' --include='pom.xml' --include='README.md' . | grep -v target | wc -l
```
Expected: `45`

- [ ] **Step 2: Bulk replace the plain occurrences**

```bash
grep -rl 'Struts 2' --include='pom.xml' --include='README.md' . | grep -v target | \
  xargs perl -pi -e 's{\bStruts 2\b(?! *\.)}{Apache Struts}g'
```

- [ ] **Step 3: Read every changed line and fix awkward phrasing**

```bash
git diff -U0 -- '*.xml' '*.md' | grep '^+' | grep -i 'apache struts'
```

A dry run of Step 2 produces exactly four lines that read badly. Fix these by hand; everything else the substitution produces is correct as-is.

**These are the strings as they exist *after* Step 2 has run — match them, not the original "Struts 2" text.**

1. Root `README.md` — `"Getting Started Apache Struts tutorials"` reads backwards. Replace:
```markdown
This Maven multi-module project contains all the Apache Struts example applications that are part of the Getting Started Apache Struts tutorials at http://struts.apache.org.
```
with:
```markdown
This Maven multi-module project contains all the Apache Struts example applications that are part of the Apache Struts Getting Started tutorials at https://struts.apache.org.
```

2. `basic-struts/README.md` — `"How To Create A Apache Struts Web Application"`. Replace `A Apache Struts` with `An Apache Struts`:
```markdown
This is the example project referred to in the Apache Struts documentation, [How To Create An Apache Struts Web Application](https://struts.apache.org/getting-started/how-to-create-a-struts2-web-application.html) tutorial.
```
Leave the URL slug (`how-to-create-a-struts2-web-application`) alone — it is a real upstream path, not prose.

3. `using-tags/pom.xml` — the description repeats the name. Replace:
```xml
    <description>Apache Struts example application for the Using Apache Struts Tags getting started tutorial</description>
```
with:
```xml
    <description>Example application for the Using Struts Tags getting started tutorial</description>
```

4. `using-tags/pom.xml` `<name>` — `Using Apache Struts Tags` is acceptable, but prefer the shorter `Using Struts Tags` for consistency with the description above.

Then confirm no duplication slipped through:
```bash
grep -rn 'Apache Struts Apache Struts\|A Apache' --include='pom.xml' --include='README.md' . | grep -v target
```
Expected: no output.

- [ ] **Step 4: Update the parent POM name and description**

In `pom.xml`, confirm the result reads:
```xml
    <name>Apache Struts Examples</name>
```
and that `<description>` reads naturally, e.g.:
```xml
    <description>
        This is the parent pom for the Apache Struts examples that
        go with the Struts Getting Started series of tutorials.
    </description>
```

- [ ] **Step 5: Fix the root README's false claim and broken link**

In `README.md`, replace:
```markdown
There is a README file in each module with instructions and the URL to view that application.
```
with:
```markdown
Some modules have a README file with instructions and the URL to view that application.
```

Replace:
```markdown
The examples are using the latest Struts version, if you are looking for older versions please take a look on the [Releases](releases) page.
```
with:
```markdown
The examples use the latest Struts version. For older versions, see the [Releases](https://github.com/apache/struts-examples/releases) page.
```

- [ ] **Step 6: Switch remaining `http://` links to `https://` in READMEs**

```bash
grep -rl 'http://' --include='README.md' . | grep -v target | \
  xargs perl -pi -e 's{http://(www\.apache\.org|img\.shields\.io|struts\.apache\.org)}{https://$1}g'
```

Verify:
```bash
grep -rn 'http://' --include='README.md' . | grep -v target | grep -v localhost
```
Expected: no output. (`http://localhost` references are correct and must stay.)

- [ ] **Step 7: Verify no "Struts 2" remains and POMs are still valid**

```bash
echo "Struts 2 left: $(grep -rn 'Struts 2' --include='pom.xml' --include='README.md' . | grep -v target | wc -l)"
find . -name 'pom.xml' -not -path '*/target/*' -print0 | xargs -0 -n1 xmllint --noout --nonet
```
Expected: `Struts 2 left: 0`, and no `xmllint` output.

- [ ] **Step 8: Build**

```bash
./mvnw -B -q clean test
```
Expected: BUILD SUCCESS.

- [ ] **Step 9: Commit**

```bash
git add -A
git commit -m "Rename to Apache Struts and refresh README links

The project has been Struts 7 for some time; the Struts 2 naming in POM
names and descriptions was stale. Also corrects the root README's claim
that every module has a README (6 of 47 do), fixes the broken Releases
link, and moves remaining http:// links to https://.

Co-Authored-By: Claude Opus 5 <noreply@anthropic.com>"
```

---

### Task 6: Full verification

**Files:** none modified.

**Interfaces:**
- Consumes: Tasks 1–5
- Produces: the verification record for the PR description

- [ ] **Step 1: Clean build on Java 17**

```bash
JAVA_HOME=$(/usr/libexec/java_home -v 17) ./mvnw -B -V clean test
```
Expected: BUILD SUCCESS, 47 modules.

- [ ] **Step 2: Clean build on Java 21**

```bash
JAVA_HOME=$(/usr/libexec/java_home -v 21) ./mvnw -B -V clean test
```
Expected: BUILD SUCCESS, 47 modules. These two mirror the CI matrix in `.github/workflows/maven.yml`.

- [ ] **Step 3: Confirm 7.3.0 introduces no new CVE**

```bash
./mvnw -B -q -P dependency-check verify
```
Expected: no failure. The profile is configured with `failBuildOnCVSS=7`, so a CVSS ≥ 7 finding fails the build. If it fails, record the CVE and raise it — do not suppress it without discussion.

- [ ] **Step 4: Confirm the commit series is exactly as planned**

```bash
git log --oneline origin/main..HEAD
```
Expected: 6 commits — the spec doc, then Tasks 1–5.

- [ ] **Step 5: Confirm nothing unintended was touched**

```bash
git diff --stat origin/main..HEAD | tail -1
```
Expected: roughly 140–150 files. The per-task counts are the authoritative check (1 parent POM, 79 DOCTYPE files, 29 `web.xml`, 5 Java, 34 naming files, with some POM overlap between the Maven-schema and naming sweeps). Review the file list for anything outside those four sweeps — in particular, no file under any `target/` directory and no `.java` file beyond the five in Task 4.

---

## Notes for the reviewer

- **Commit 2 of the spec became two commits** (Tasks 2 and 3). The DOCTYPE sweep is a regex over 79 files; the `web.xml` migration rewrites a root element in 29 files via a script. Different mechanisms and different risk, so they are reviewed separately.
- **The annotation audit found 8 fixes, not the 8–12 the spec estimated** — within the predicted range. Modules deliberately left alone, with reasons:
  - `rest-angular` — `OrderController` is `ModelDriven`, and `ParameterAuthorizer` documents the model as exempt from annotation requirements. `IndexController.useMinifiedResources` is only read by a JSP `<s:if>`, never submitted.
  - `annotations` — `HelloAction.message` is assigned inside `execute()` and only rendered; it is not an input.
  - `text-provider` — `SystemAction.setTextProvider` is `@Inject`-driven, not a request parameter.
  - `file-upload` and `sitemesh3` — `UploadAction implements UploadedFilesAware` and receives files via `withUploadedFiles(List<UploadedFile>)`. There is no `setUpload`; `<s:file>` is consumed by the file-upload interceptor, not `ParametersInterceptor`.
  - `struts-parameter` — `users[%{#status.index}].id` evaluates to `users[0].id`, depth 2. The existing `@StrutsParameter(depth = 2)` is already correct.
- **No `unverified` rows.** Every action's parameters were traceable to a JSP form, a validation descriptor, or an assignment in `execute()`.
