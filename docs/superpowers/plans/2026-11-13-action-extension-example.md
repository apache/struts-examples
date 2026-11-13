# `action-extension` Example Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** A new standalone `action-extension` example module that demonstrates global, package-level and action-level action extensions (Apache Struts 7.5.0, WW-4164).

**Architecture:** One `struts.xml` with two packages: `default` at `/`, which uses the global `struts.action.extension=action`, and `legacy` at `/legacy` with `extension="do"`, which holds one action declaring `extension=""`. The actions have no classes; they use the default `ActionSupport` and point at JSPs under `WEB-INF/jsp/`. A `StrutsTestCase` test pins the inbound mapping and the rejection. A Jetty smoke check covers the outbound links.

**Tech Stack:** Apache Struts 7.5.0, Jakarta Servlet 6 / JSP, `struts2-junit-plugin`, Jetty Maven plugin.

**Spec:** `docs/superpowers/specs/2026-11-13-action-extension-example-design.md`

## Global Constraints

- Struts config DOCTYPE: `-//Apache Software Foundation//DTD Struts Configuration 7.5//EN` / `https://struts.apache.org/dtds/struts-7.5.dtd`.
- `web.xml` on `web-app_6_0.xsd`, `version="6.0"`; `jakarta.*` only.
- Call the framework "Apache Struts", never "Struts 2" (this includes `<display-name>` and the pom `<name>`).
- Module versions come from the root pom; the module pom declares only `struts2-junit-plugin` (test) and `spring-web` (test), copied from `unit-testing/pom.xml`.
- The README states that extensions are URL canonicalisation, not access control.

## Review Focus

1. **Welcome URL** `http://localhost:8080/action-extension/`: the global list has no empty entry, so a `/index` welcome file would not map. A reader opening the root URL must still land on the index page. Pinned by the Task 2 smoke check (`curl -L /action-extension/` shows the index).
2. **Outbound from an extensionless page**: links rendered on `legacy/report` must still emit `hello.action` and `legacy/orders.do`, not inherit "no extension". Pinned by the Task 2 smoke check on `report`.
3. **Outbound from a `.do` page to a global action**: `orders.do` linking to `hello` must emit `hello.action`. Pinned by the Task 2 smoke check on `orders.do`.
4. **Direct JSP access** `/action-extension/WEB-INF/jsp/hello.jsp`: must not render, because pages live under `WEB-INF`. Pinned by the Task 2 smoke check (expect 404).
5. **Undeclared action on a foreign extension** `hello.do`: the reader expects a 404, not the bad-extension page. Pinned by `testUndeclaredActionDoesNotClaimDo` (Task 1) and the Task 2 smoke check.

---

### Task 1: Module, configuration and mapping test

**Files:**
- Create: `action-extension/pom.xml`
- Create: `action-extension/src/main/resources/struts.xml`
- Create: `action-extension/src/main/resources/log4j2.xml` (copy of `helloworld/src/main/resources/log4j2.xml`)
- Create: `action-extension/src/main/webapp/WEB-INF/web.xml`
- Create: `action-extension/src/test/java/org/apache/struts_example/ActionExtensionTest.java`
- Modify: `pom.xml` (root): add `<module>action-extension</module>` after `<module>sitemesh3</module>`

**Interfaces:**
- Produces: action names and results that Task 2's JSPs link to:
  - namespace `/`: `index` → `/WEB-INF/jsp/index.jsp`, `hello` → `/WEB-INF/jsp/hello.jsp`
  - namespace `/legacy`: `orders` → `/WEB-INF/jsp/orders.jsp`, `report` → `/WEB-INF/jsp/report.jsp`
  - global result `bad-extension` → `/WEB-INF/jsp/badExtension.jsp` in **both** packages (`legacy` does not extend `default`; each declares its own `<global-results>`)

- [ ] **Step 1: Create `action-extension/pom.xml`**

Copy `helloworld/pom.xml` with `artifactId` set to `action-extension`, `<name>Action Extension Apache Struts Example Application</name>`, a matching `<description>`, `<finalName>action-extension</finalName>`, and the `<dependencies>` block from `unit-testing/pom.xml`. Add the module to the root `pom.xml`.

- [ ] **Step 2: Write the failing test**

`ActionExtensionTest extends org.apache.struts2.junit.StrutsTestCase`, JUnit 4 `@Test` methods, following the style of `unit-testing/.../RegisterTest.java`:

```java
@Test public void testGlobalExtensionMapsHello()        // getActionMapping("/hello.action"): name "hello", namespace "/", extension "action"
@Test public void testPackageExtensionMapsOrders()      // getActionMapping("/legacy/orders.do"): name "orders", namespace "/legacy", extension "do"
@Test public void testActionExtensionMapsReport()       // getActionMapping("/legacy/report"): name "report", namespace "/legacy"
@Test public void testUndeclaredActionDoesNotClaimDo()  // assertNull(getActionMapping("/hello.do"))
@Test public void testDeclaredExtensionsExecute()       // getActionProxy(uri).execute() == "success" for "/hello.action", "/legacy/orders.do", "/legacy/report"
@Test public void testOrdersRejectsActionExtension()    // getActionProxy("/legacy/orders.action").execute() == "bad-extension"
@Test public void testReportRejectsDoExtension()        // getActionProxy("/legacy/report.do").execute() == "bad-extension"
```

Create a fresh request between the proxies in `testDeclaredExtensionsExecute`, or split it into three methods if reusing one request leaks state.

- [ ] **Step 3: Run the test to verify it fails**

Run: `./mvnw -pl action-extension test`
Expected: FAIL. With no `struts.xml` yet, the mappings are null or the proxies cannot be created.

- [ ] **Step 4: Write `web.xml` and `struts.xml`**

`web.xml`: copy `helloworld`'s, with `<display-name>Action Extension Apache Struts Example</display-name>` and `<welcome-file>index.jsp</welcome-file>` (the top-level redirect page comes in Task 2).

`struts.xml` (the exact structure is the teaching content):

```xml
<constant name="struts.devMode" value="true"/>
<constant name="struts.action.extension" value="action"/>

<package name="default" namespace="/" extends="struts-default">
    <global-results><result name="bad-extension">/WEB-INF/jsp/badExtension.jsp</result></global-results>
    <action name="index"><result>/WEB-INF/jsp/index.jsp</result></action>
    <action name="hello"><result>/WEB-INF/jsp/hello.jsp</result></action>
</package>

<package name="legacy" namespace="/legacy" extends="struts-default" extension="do">
    <global-results><result name="bad-extension">/WEB-INF/jsp/badExtension.jsp</result></global-results>
    <action name="orders"><result>/WEB-INF/jsp/orders.jsp</result></action>
    <action name="report" extension=""><result>/WEB-INF/jsp/report.jsp</result></action>
</package>
```

Each of the three levels gets a one-line XML comment naming it (global / package override / action override). Keep `struts.enable.DynamicMethodInvocation=false` as in the other examples.

- [ ] **Step 5: Run the test to verify it passes**

Run: `./mvnw -pl action-extension test`
Expected: `Tests run: 7, Failures: 0, Errors: 0`. JSP rendering is not exercised by the mock dispatcher, so missing JSPs do not fail the test.

- [ ] **Step 6: Commit**

```bash
git add pom.xml action-extension
git commit -m "feat(action-extension): add module with global, package and action extensions"
```

### Task 2: Pages, README and outbound smoke check

**Files:**
- Create: `action-extension/src/main/webapp/index.jsp`: redirects to `index.action` (`<% response.sendRedirect("index.action"); %>`)
- Create: `action-extension/src/main/webapp/WEB-INF/jsp/index.jsp`, `hello.jsp`, `orders.jsp`, `report.jsp`, `badExtension.jsp`
- Create: `action-extension/README.md`

**Interfaces:**
- Consumes: the action names, namespaces and results from Task 1.

- [ ] **Step 1: Write the pages**

- A shared link block appears on `WEB-INF/jsp/index.jsp` and on each result page (inline it or use `<%@ include file="links.jspf" %>`; builder's choice). For each of `hello` (ns `/`), `orders` (ns `/legacy`) and `report` (ns `/legacy`), the block has:
  - an `<s:a>` link built with `<s:url var=... action=... namespace=.../>`, with the generated URL printed next to it via `<s:property value="#var"/>`;
  - a plain `<a href="...">` wrong-extension link: `${pageContext.request.contextPath}/hello.do`, `.../legacy/orders.action`, `.../legacy/report.do`.
- Each result page says in one or two sentences which layer it demonstrates and where that layer is configured.
- `badExtension.jsp` explains that the `actionExtension` interceptor rejected the request because the URL's extension is not in the action's declared list. It shows the requested URI (`${pageContext.request.requestURI}` is the forwarded JSP path, so use `${requestScope['jakarta.servlet.forward.request_uri']}`) and links back to `index.action`.
- The index page also explains why `hello.do` is a plain 404: `hello` declares no extension, so only the global list (`action`) applies, and `.do` is never mapped to it.

- [ ] **Step 2: Write `README.md`**

Contents: one-paragraph purpose; run command `./mvnw -pl action-extension jetty:run`; URL `http://localhost:8080/action-extension/`; the three-layer table from the spec; resolution order "action → package → parent packages → `struts.action.extension`"; list format (comma-separated, first entry is the default for generated URLs, an empty entry means no extension); a note that a wrong extension goes to the `bad-extension` result or a 404; a note that this is URL canonicalisation, not access control; requires Apache Struts 7.5.0 or later.

- [ ] **Step 3: Build and run**

Run: `./mvnw -pl action-extension clean package` → `BUILD SUCCESS`.
Then start `./mvnw -pl action-extension jetty:run` in the background and wait for `Started Server`.

- [ ] **Step 4: Smoke-check inbound and outbound**

```bash
B=http://localhost:8080/action-extension
curl -sL  $B/                         | grep -q 'hello.action'               # welcome redirect lands on index
curl -s   $B/index.action             | grep -oE 'href="[^"]*"'              # expect /action-extension/hello.action, /action-extension/legacy/orders.do, /action-extension/legacy/report
curl -s   $B/legacy/report            | grep -oE 'href="[^"]*"'              # expect the same three generated hrefs
curl -s   $B/legacy/orders.do         | grep -oE 'href="[^"]*"'              # expect the same three generated hrefs
curl -s -o /dev/null -w '%{http_code}\n' $B/hello.do                          # 404
curl -s   $B/legacy/orders.action     | grep -qi 'rejected'                  # bad-extension page
curl -s   $B/legacy/report.do         | grep -qi 'rejected'                  # bad-extension page
curl -s -o /dev/null -w '%{http_code}\n' $B/WEB-INF/jsp/hello.jsp             # 404
```

Expected: every check holds. Stop Jetty afterwards.

- [ ] **Step 5: Full reactor build**

Run: `./mvnw -B clean package` → `BUILD SUCCESS`.

- [ ] **Step 6: Commit**

```bash
git add action-extension
git commit -m "feat(action-extension): add pages and README showing generated URLs and rejection"
```
