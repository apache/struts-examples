This Maven multi-module project contains all the Struts 2
example applications that are part of the Getting Started
Struts 2 tutorials at http://struts.apache.org.

To build all the example applications run the Maven command:
mvn -e clean package in the project's root folder.  Maven 
will the build each module and create a .war file in the
target sub-folder of each module.

You can then copy the .war files to your Servlet container
(e.g. Tomcat, Jetty, GlassFish, etc).

There is a ReadMe.txt file in each module with instructions 
and the URL to view that application.
