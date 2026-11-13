# `action-extension` example — design

- **Feature:** per-package / per-action `extension` attribute, Apache Struts 7.5.0
  ([WW-4164](https://issues.apache.org/jira/browse/WW-4164))
- **Date:** 2026-11-13

## Intent

A tutorial-ready example of the three layers of action extension resolution: the global
`struts.action.extension`, an override on `<package>`, and an override on `<action>`. It shows
both inbound mapping (which URL reaches which action) and outbound URL generation (tags emit the
target action's extension), plus what happens on a wrong extension.

## Goals

- New standalone module `action-extension`, registered in the root `<modules>`, runnable with
  `./mvnw -pl action-extension jetty:run` at `http://localhost:8080/action-extension/`.
- Three layers, each with its own URL prefix:

  | Layer | Config | Action | URL |
  |---|---|---|---|
  | Global | `<constant name="struts.action.extension" value="action"/>` | `/index`, `/hello` | `index.action`, `hello.action` |
  | Package | `<package name="legacy" namespace="/legacy" extension="do">` | `/legacy/orders` | `legacy/orders.do` |
  | Action | `<action name="report" extension="">` inside `legacy` | `/legacy/report` | `legacy/report` |

- `index.jsp` links to each action with `s:a`, so readers see the generated URL pick up the
  target's extension. It also prints the generated URL as text next to each link.
- Each layer also gets one "wrong extension" link, written as a plain `<a href>` because `s:a`
  would correct the extension:
  - `legacy/orders.action` and `legacy/report.do` → rejected by the `actionExtension`
    interceptor → a mapped `bad-extension` global result (`badExtension.jsp`), which explains why.
  - `hello.do` → container 404. `hello` declares no extension, so it never claims `.do`, and the
    global list has no empty entry. The page text explains this, because it is the "undeclared
    actions follow the global list only" rule.
- The result pages (`hello.jsp`, `orders.jsp`, `report.jsp`) link back to the others with `s:a`.
  This shows that outbound URLs follow the *target's* list, not the current page's extension.
- `README.md` with the run command, the URLs, and the resolution order
  (action → package → parent packages → global).
- A `StrutsJUnit4TestCase` test pinning the three mappings and the `bad-extension` rejection.

## Non-goals

- Convention-plugin / annotation extensions (not supported in 7.5.0).
- REST or other non-default action mappers.
- Changes to any other module.

## Anti-goals

- Presenting extensions as access control. The README says they are for URL canonicalisation,
  not for hiding actions.

## Constraints

- Struts 7 conventions from `CLAUDE.md`: `struts-7.5.dtd`, `web-app_6_0.xsd`, Jakarta only,
  "Apache Struts" naming.
- Any action that takes request parameters needs `@StrutsParameter`. The example has no form
  input, so probably none.
- `actionExtension` is already in the default stacks. Packages extend `struts-default` and keep
  the default stack, so the startup "interceptor missing" warning doesn't fire.

## Approaches

- **A. Two packages in separate namespaces (`/` and `/legacy`)**: picked. Each layer has a
  distinct URL prefix, which is easiest to follow in a tutorial.
- **B. Two packages in the same namespace `/`**: shows that extension is set per package, not per
  namespace, but the URLs for different layers end up side by side and harder to tell apart.
- Scope option B (mapping + generated links + rejection) was picked over mapping only.

## Left to the builder

- Action classes: one small `ActionSupport` subclass per action, or plain result-only actions
  where nothing is computed.
- Exact page copy and styling (keep it as plain as the other examples).
- Whether the test drives the mapping through `getActionMapping` / `executeAction` or asserts
  through the `ActionMapper` directly. Check that `StrutsJUnit4TestCase` routes through the
  configured mapper with the configuration manager. Otherwise the declared extensions are not seen.
