<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Debugging Struts 2 - Security Error</title>
</head>
<body>
<h4>There has been a security error.</h4>

<p> Please contact technical support with the following information:</p>

<!-- the exception and exceptionStack bean properties
were created by Struts2's Exception Interceptor (see page 89) -->
<h3>Exception Name: <s:property value="exception"/></h3>
<pre>Exception Details: <s:property value="exceptionStack"/></pre>

<p><s:a action="index">Return to the home page.</s:a></p>
</body>
</html>
