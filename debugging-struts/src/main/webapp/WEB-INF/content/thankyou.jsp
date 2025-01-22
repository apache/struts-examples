<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Debugging Struts 2 - Registration Successful</title>
</head>

<body>
<h3>Thank you for registering for a prize.</h3>

<p>Your registration information:</p>
<pre><s:property value="personBean"/> </pre>

<p><s:a action="index">Return to the home page.</s:a></p>
<hr>

<s:debug/>
</body>
</html>