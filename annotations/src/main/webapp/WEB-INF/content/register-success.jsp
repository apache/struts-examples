<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Basic Struts 2 Application - Registration Successful</title>
</head>
<body>
<h3>Thank you for registering for a prize.</h3>

<p>Your registration information: <s:property value="personBean"/></p>

<p><a href="<s:url action='index'  />">Return to home page</a>.</p>

</body>
</html>