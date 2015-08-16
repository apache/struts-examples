<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
<h4>Exception Name: <s:property value="exception"/></h4>
<h4>Exception Details: <s:property value="exceptionStack"/></h4>

<p><a href="index.jsp">Return to the home page.</a></p>
</body>
</html>
