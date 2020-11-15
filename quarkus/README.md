# Quarkus example

This is a simple example how to use Struts 2 with [Quarkus](https://quarkus.io/). Quarkus doesn't support JSPs
but yoy can use Freemarker instead or writing directly into HttpServletResponse.

In `application.properties` has been defined `quarkus.oidc.enabled=false` to disable redirecting to `/auth` even 
if the "oidc" extension has not been loaded. You will also notice a warning when starting Quarkus - just ignore it.

## Running

Use the below command to start the example Quarkus application (from within the project's root folder):

```
mvn clean compile quarkus:dev
``` 
