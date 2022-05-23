Struts Examples
---------------

[![Build Status](https://ci-builds.apache.org/buildStatus/icon?job=Struts%2FStruts-examples-master)](https://ci-builds.apache.org/job/Struts/job/Struts-examples-master/)
[![Build Status @ Travis](https://travis-ci.org/apache/struts-examples.svg?branch=master)](https://travis-ci.org/apache/struts-examples)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)


This Maven multi-module project contains all the Struts 2 example applications that are part of the Getting Started Struts 2 tutorials at http://struts.apache.org.

To build all the example applications run the Maven command:

```
mvn -e clean package
```

In the project's root folder, Maven will build each module and create a `.war` file in the target sub-folder of each module.

You can then copy the `.war` files to your Servlet container (e.g. Tomcat, Jetty, GlassFish, etc).

There is a README file in each module with instructions and the URL to view that application.
