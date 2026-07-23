# WW-5416: Hello World example returns 404 — design

- **JIRA:** [WW-5416](https://issues.apache.org/jira/browse/WW-5416)
- **Fix version:** 7.3.0
- **Date:** 2026-07-23

## Problem

Following the documented Hello World instructions produces a 404. The tutorial (and
`helloworld/README.txt`) tell users to visit `http://localhost:8080/helloworld/index.action`,
but the app returns 404 for any `.action` URL.

### Root cause

`helloworld/src/main/resources/struts.xml` sets:

```xml
<constant name="struts.action.extension" value=","/>
```

The framework default is `action,,`, which maps **both** `.action` URLs and extensionless
URLs. Overriding it with `,` reduces the allowed set to the empty extension only, so
`.action` URLs no longer resolve and 404.

The examples' own pages navigate via Struts URL/form tags (e.g. `<s:url action="hello"/>`),
which emit URLs using whatever extension is configured — so in-app navigation "works" and
the bug is invisible until a user types the documented `.action` URL directly.

### Same latent bug in other examples

| Example | `struts.action.extension` | Verdict |
|---|---|---|
| `helloworld` | `","` | Bug — fix (the ticket) |
| `text-provider` | `","` | Same latent bug — fix |
| `sitemesh3` | `""` | Same latent bug — fix |
| `rest-angular` | `",,xml,json,action"` | Intentional (REST/JSON demo) — leave |
| `mailreader2` | `"do"` | Intentional (Struts1 `.do` migration) — leave |

## Approach

Delete the `struts.action.extension` override in the three affected modules, restoring the
framework default `action,,`.

**Rejected alternative:** setting the value explicitly to `action,,`. Same runtime effect but
adds a config line that only restates the default — no teaching value for example apps.

## Change set

Three one-line deletions:

- `helloworld/src/main/resources/struts.xml` — remove `<constant name="struts.action.extension" value=","/>`
- `text-provider/src/main/resources/struts.xml` — remove `<constant name="struts.action.extension" value=","/>`
- `sitemesh3/src/main/resources/struts.xml` — remove `<constant name="struts.action.extension" value=""/>`

`rest-angular` and `mailreader2` are explicitly left unchanged.

## Behavior after fix

- `<s:url action="..."/>` tags emit `.action` URLs by default → in-app links match the tutorials.
- Directly-typed documented URLs (`/helloworld/index.action`, `/helloworld/hello.action`) return 200.
- Extensionless URLs (`/hello`) still resolve — the default is a superset, so nothing that
  currently navigates breaks.

## Risk

Low / additive. No Java code in these modules inspects the URL extension; all navigation
routes through Struts tags that respect the configured extension. Widening the allowed
extension set cannot remove a previously working route.

## Out of scope

- The authoritative "Getting Started" tutorial text lives in the separate `struts-site`
  repository; not edited here. `helloworld/README.txt` already references `.action` and
  becomes correct once the override is removed.
- No new automated/integration test (per scope decision). Regression is guarded only by the
  existing full build.

## Verification

- `mvn clean package` succeeds for all modules.
- For `helloworld`, `text-provider`, and `sitemesh3`: `mvn jetty:run`, then confirm the
  documented `.action` URL returns 200 and the in-app links navigate correctly.
