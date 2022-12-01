<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Struts 2 Form Tags - Welcome</title>
</head>

<body>
<h1>Welcome To Struts 2!</h1>

<p><a href='<s:url action="edit" includeParams="all" />'>Edit your information</a></p>
</body>
</html>
