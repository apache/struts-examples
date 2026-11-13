# Action Extension

This example shows the three places where an action extension can be set in Apache Struts:
globally, on a `<package>`, and on a single `<action>`. It covers both which request URLs reach
an action and which extension the Struts tags use when they generate a URL to it.

Requires Apache Struts 7.5.0 or later.

## Run

```bash
./mvnw -pl action-extension jetty:run
```

Then open http://localhost:8080/action-extension/

## What is configured

| Layer | Config in `struts.xml` | Action | URL |
|---|---|---|---|
| Global | `<constant name="struts.action.extension" value="action"/>` | `hello` | `/hello.action` |
| Package | `<package name="legacy" namespace="/legacy" extension="do">` | `orders` | `/legacy/orders.do` |
| Action | `<action name="report" extension="">` | `report` | `/legacy/report` |

An action's extensions are taken from the most specific place that declares them:

action → package → parent packages → `struts.action.extension`

Every place uses the same format: a comma-separated list. The first entry is the one generated
URLs use, and an empty entry means "no extension" (`extension=""`, or `action,,`).

## Generated URLs

`<s:url>`, `<s:a>`, `<s:form>` and the `redirectAction` result build the URL from the
**target** action's extensions. A link to `orders` from `/legacy/report` comes out as
`/legacy/orders.do`, and a link to `hello` from `/legacy/orders.do` comes out as `/hello.action`.

## Wrong extensions

- `/legacy/orders.action` and `/legacy/report.do` reach an action that declares its own
  extensions. The `actionExtension` interceptor rejects them and returns the `bad-extension`
  result. Without a `bad-extension` result, the response is a 404.
- `/hello.do` is a plain 404. `hello` declares no extension, so only the global list applies to
  it, and `.do` is never mapped to it.

Extensions control which URL is the canonical one for an action. They are not access control:
do not use them to hide actions.
