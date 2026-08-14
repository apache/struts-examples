# Struts Examples

[![Build Status @ ASF](https://ci-builds.apache.org/buildStatus/icon?job=Struts%2FStruts-examples-master)](https://ci-builds.apache.org/job/Struts/job/Struts-examples-master/)
[![Build Status @ GH Actions](https://github.com/apache/struts-examples/actions/workflows/maven.yml/badge.svg)](https://github.com/apache/struts-examples/actions/workflows/maven.yml)
[![License](https://img.shields.io/:license-apache-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

This Maven multi-module project contains all the Apache Struts example applications that are part of the Apache Struts Getting Started tutorials at https://struts.apache.org.

To build all the example applications run the Maven command:

```
mvn -e clean package
```

In the project's root folder, Maven will build each module and create a `.war` file in the target sub-folder of each module.

You can then copy the `.war` files to your Servlet container (e.g. Tomcat, Jetty, GlassFish, etc).

Some modules have a README file with instructions and the URL to view that application.

## Older versions

The examples use the latest Struts version. For older versions, see the [Releases](https://github.com/apache/struts-examples/releases) page.
