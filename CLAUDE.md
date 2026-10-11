# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this is

A Maven multi-module (reactor) project of standalone Apache Struts 7 example webapps that back the
Getting Started tutorials at https://struts.apache.org. Each module is an independent `.war`; modules
do not depend on each other. Issues are tracked in JIRA project `WW`.

## Commands

Java 17 (CI also builds on 21). Use the wrapper `./mvnw` or `mvn`.

```bash
./mvnw -e clean package                               # build every module's .war (README's command)
./mvnw -B test                                        # what CI runs (.github/workflows/maven.yml)
./mvnw -pl blank test                                 # tests for one module
./mvnw -pl blank test -Dtest=LoginTest                # single test class
./mvnw -pl blank test -Dtest=LoginTest#testLoginSubmit # single test method
./mvnw -pl helloworld jetty:run                       # run one example at http://localhost:8080/<artifactId>/
./mvnw -Pdependency-check verify                      # OWASP check, fails on CVSS >= 7; suppressions in src/etc/project-suppression.xml
(cd quarkus && ../mvnw clean compile quarkus:dev)     # quarkus module runs on Quarkus, not Jetty, and uses FreeMarker (no JSP)
```

Only a few modules have tests (`blank`, `unit-testing`, `rest-angular`, `text-provider`); most
examples are verified by running them. Many modules carry a `README.txt`/`README.md` with the
tutorial link and the URL to open.

## Structure

- The root `pom.xml` is the single source of versions: `struts2.version`, `log4j2.version`,
  `jetty-plugin.version`, Jackson BOM, Jakarta Servlet/JSP APIs (`provided`). Every module inherits
  `struts2-core`, `log4j-core` and the servlet/JSP APIs; modules add only the Struts plugins they
  demonstrate (convention, json, rest, spring, tiles, junit, bean-validation, jasperreports, …).
- A new module must be added to `<modules>` in the root `pom.xml` and normally copies the
  `jetty-maven-plugin` block from an existing module (context path `/${project.artifactId}`).
- Typical module layout: actions in `src/main/java`, `struts.xml` + `log4j2.xml` + message bundles
  in `src/main/resources`, JSPs and `WEB-INF/web.xml` under `src/main/webapp`. Modules using the
  convention plugin (`annotations`, `rest-angular`, `text-provider`, `unknown-handler`) configure
  actions via annotations/naming instead of `struts.xml`.

## Struts 7 conventions to preserve

These examples are teaching material, so keep config files on the current standard:

- Struts config DOCTYPE: `struts-6.5.dtd` (`https://struts.apache.org/dtds/struts-6.5.dtd`);
  `web.xml` on `web-app_6_0.xsd`, `version="6.0"`; Jakarta (`jakarta.*`) namespaces only.
- **`@StrutsParameter` is required** for request parameters to reach an action
  (`struts.parameters.requireAnnotations=true` is the default). A missing annotation silently drops
  the parameter rather than failing. Rules:
  - Only the action class is checked; model beans (`Person`, `MessageStore`, …) need no annotation.
  - Top-level param (`name`) → annotate the **setter**.
  - Nested param (`personBean.firstName`, `items[0]`) → annotate the **getter** with
    `depth = N` (number of `.`/`[` in the longest name under that root). Annotating the setter
    has no effect for nested params.
  - The same rules apply to JSON/REST plugin bodies and `CookieInterceptor`.
- Leave existing `struts.allowlist.*` settings in place even when they look redundant.
- Refer to the framework as "Apache Struts", not "Struts 2".

Design notes and plans for larger changes live in `docs/superpowers/` (specs and plans).
