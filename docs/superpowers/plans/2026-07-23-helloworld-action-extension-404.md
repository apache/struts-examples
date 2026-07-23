# WW-5416 action.extension 404 Fix — Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Restore the framework-default `struts.action.extension` in the `helloworld`, `text-provider`, and `sitemesh3` example modules so documented `.action` URLs return 200 instead of 404.

**Architecture:** Each affected module overrides `struts.action.extension` to a value that excludes the `.action` extension. Deleting that one `<constant>` line restores the framework default `action,,`, which accepts both `.action` and extensionless URLs. In-app navigation already routes through Struts URL/form tags, so widening the allowed extension set is strictly additive — no existing route breaks.

**Tech Stack:** Struts 2 (XML config), Maven multi-module build, Jetty Maven plugin for local run.

## Global Constraints

- Do **not** modify `rest-angular` (`struts.action.extension=,,xml,json,action`) or `mailreader2` (`struts.action.extension=do`) — those overrides are intentional.
- No new automated/integration test is added (per spec scope decision).
- Commit messages use the repo convention: `WW-5416 fix(<module>): <description>` (JIRA ticket prefix required).
- Work happens on branch `fix/WW-5416-action-extension-404` (already created).
- Each task must keep `mvn -pl <module> clean package` green.

---

### Task 1: Fix `helloworld` (the ticket)

**Files:**
- Modify: `helloworld/src/main/resources/struts.xml:9`

**Interfaces:**
- Consumes: nothing from other tasks.
- Produces: nothing other tasks depend on (each task is independent).

- [ ] **Step 1: Confirm the bug exists (baseline)**

Start the app in the background and confirm the documented URL 404s:

```bash
cd /Users/lukaszlenart/Projects/Apache/struts-examples/helloworld
mvn -q jetty:run &
JETTY_PID=$!
# wait for startup
until curl -s -o /dev/null http://localhost:8080/helloworld/ ; do sleep 2; done
curl -s -o /dev/null -w "index.action -> %{http_code}\n" http://localhost:8080/helloworld/index.action
kill $JETTY_PID
```

Expected (before fix): `index.action -> 404`

- [ ] **Step 2: Delete the extension override**

In `helloworld/src/main/resources/struts.xml`, remove this line (line 9):

```xml
    <constant name="struts.action.extension" value=","/>
```

Leave `struts.devMode` and `struts.allowlist.packageNames` constants untouched.

- [ ] **Step 3: Verify the build still passes**

Run: `mvn -q -pl helloworld clean package`
Expected: `BUILD SUCCESS`

- [ ] **Step 4: Verify the documented URL now returns 200**

```bash
cd /Users/lukaszlenart/Projects/Apache/struts-examples/helloworld
mvn -q jetty:run &
JETTY_PID=$!
until curl -s -o /dev/null http://localhost:8080/helloworld/ ; do sleep 2; done
curl -s -o /dev/null -w "index.action -> %{http_code}\n" http://localhost:8080/helloworld/index.action
curl -s -o /dev/null -w "hello.action -> %{http_code}\n"  http://localhost:8080/helloworld/hello.action
curl -s -o /dev/null -w "hello (no ext) -> %{http_code}\n" http://localhost:8080/helloworld/hello
kill $JETTY_PID
```

Expected (after fix):
```
index.action -> 200
hello.action -> 200
hello (no ext) -> 200
```

- [ ] **Step 5: Commit**

```bash
git add helloworld/src/main/resources/struts.xml
git commit -m "WW-5416 fix(helloworld): restore default struts.action.extension

Removing the ',' override restores the framework default 'action,,' so
the documented .action URLs return 200 instead of 404.

Co-Authored-By: Claude Opus 4.8 <noreply@anthropic.com>"
```

---

### Task 2: Fix `text-provider`

**Files:**
- Modify: `text-provider/src/main/resources/struts.xml:8`

**Interfaces:**
- Consumes: nothing from other tasks.
- Produces: nothing other tasks depend on.

- [ ] **Step 1: Confirm the bug exists (baseline)**

```bash
cd /Users/lukaszlenart/Projects/Apache/struts-examples/text-provider
mvn -q jetty:run &
JETTY_PID=$!
until curl -s -o /dev/null http://localhost:8080/text-provider/ ; do sleep 2; done
curl -s -o /dev/null -w "index.action -> %{http_code}\n" http://localhost:8080/text-provider/index.action
kill $JETTY_PID
```

Expected (before fix): `index.action -> 404`

- [ ] **Step 2: Delete the extension override**

In `text-provider/src/main/resources/struts.xml`, remove this line (line 8):

```xml
  <constant name="struts.action.extension" value=","/>
```

Leave the `struts.convention.action.packages`, `struts.custom.i18n.resources`, and all `bean`/`TextProviderFactory` constants untouched.

- [ ] **Step 3: Verify the build still passes**

Run: `mvn -q -pl text-provider clean package`
Expected: `BUILD SUCCESS`

- [ ] **Step 4: Verify the documented URL now returns 200**

```bash
cd /Users/lukaszlenart/Projects/Apache/struts-examples/text-provider
mvn -q jetty:run &
JETTY_PID=$!
until curl -s -o /dev/null http://localhost:8080/text-provider/ ; do sleep 2; done
curl -s -o /dev/null -w "index.action -> %{http_code}\n"  http://localhost:8080/text-provider/index.action
curl -s -o /dev/null -w "index (no ext) -> %{http_code}\n" http://localhost:8080/text-provider/index
kill $JETTY_PID
```

Expected (after fix):
```
index.action -> 200
index (no ext) -> 200
```

- [ ] **Step 5: Commit**

```bash
git add text-provider/src/main/resources/struts.xml
git commit -m "WW-5416 fix(text-provider): restore default struts.action.extension

Removing the ',' override restores the framework default 'action,,' so
.action URLs no longer 404.

Co-Authored-By: Claude Opus 4.8 <noreply@anthropic.com>"
```

---

### Task 3: Fix `sitemesh3`

**Files:**
- Modify: `sitemesh3/src/main/resources/struts.xml:8`

**Interfaces:**
- Consumes: nothing from other tasks.
- Produces: nothing other tasks depend on.

- [ ] **Step 1: Confirm the bug exists (baseline)**

```bash
cd /Users/lukaszlenart/Projects/Apache/struts-examples/sitemesh3
mvn -q jetty:run &
JETTY_PID=$!
until curl -s -o /dev/null http://localhost:8080/sitemesh3/ ; do sleep 2; done
curl -s -o /dev/null -w "hello.action -> %{http_code}\n" http://localhost:8080/sitemesh3/hello.action
kill $JETTY_PID
```

Expected (before fix): `hello.action -> 404`

- [ ] **Step 2: Delete the extension override**

In `sitemesh3/src/main/resources/struts.xml`, remove this line (line 8):

```xml
    <constant name="struts.action.extension" value=""/>
```

Leave `struts.devMode`, `struts.ui.theme`, and `struts.custom.i18n.resources` constants untouched.

- [ ] **Step 3: Verify the build still passes**

Run: `mvn -q -pl sitemesh3 clean package`
Expected: `BUILD SUCCESS`

- [ ] **Step 4: Verify the documented URL now returns 200 and SiteMesh decoration still applies**

```bash
cd /Users/lukaszlenart/Projects/Apache/struts-examples/sitemesh3
mvn -q jetty:run &
JETTY_PID=$!
until curl -s -o /dev/null http://localhost:8080/sitemesh3/ ; do sleep 2; done
curl -s -o /dev/null -w "hello.action -> %{http_code}\n"  http://localhost:8080/sitemesh3/hello.action
curl -s -o /dev/null -w "hello (no ext) -> %{http_code}\n" http://localhost:8080/sitemesh3/hello
curl -s -o /dev/null -w "admin/hello.action -> %{http_code}\n" http://localhost:8080/sitemesh3/admin/hello.action
kill $JETTY_PID
```

Expected (after fix):
```
hello.action -> 200
hello (no ext) -> 200
admin/hello.action -> 200
```

- [ ] **Step 5: Commit**

```bash
git add sitemesh3/src/main/resources/struts.xml
git commit -m "WW-5416 fix(sitemesh3): restore default struts.action.extension

Removing the empty-string override restores the framework default
'action,,' so .action URLs no longer 404.

Co-Authored-By: Claude Opus 4.8 <noreply@anthropic.com>"
```

---

### Task 4: Full multi-module build verification

**Files:**
- None modified. Final gate across the whole repo.

**Interfaces:**
- Consumes: the three fixes from Tasks 1–3.
- Produces: confirmation that the reactor build is green.

- [ ] **Step 1: Build every module**

Run:
```bash
cd /Users/lukaszlenart/Projects/Apache/struts-examples
mvn -q clean package
```
Expected: `BUILD SUCCESS` with all modules reactored.

- [ ] **Step 2: Confirm no unintended changes to out-of-scope modules**

Run: `git diff main --stat`
Expected: only these three files changed (plus the docs added earlier):
```
helloworld/src/main/resources/struts.xml
text-provider/src/main/resources/struts.xml
sitemesh3/src/main/resources/struts.xml
```
Confirm `rest-angular` and `mailreader2` are NOT listed.

- [ ] **Step 3: (No commit)** — this task only verifies; nothing new to commit.
