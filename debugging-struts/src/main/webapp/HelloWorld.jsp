<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Debugging Struts 2 - Hello World!</title>
</head>
<body>
<h2><s:property value="messageStore.message"/></h2>

<p>I've said hello <s:property value="helloCount"/> times!</p>

<p><s:property value="messageStore"/></p>

<p><a href="<s:url action='index' />">Return to home page</a>.</p>

<hr>

<s:debug/>
</body>
</html>