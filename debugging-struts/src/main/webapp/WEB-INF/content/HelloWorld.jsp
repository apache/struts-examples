<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Debugging Struts 2 - Hello World!</title>
</head>
<body>
<h2><s:property value="messageStore.message"/></h2>

<p>I've said hello <s:property value="helloCount"/> times!</p>

<p><s:property value="messageStore"/></p>

<p><s:a action="index">Return to the home page.</s:a></p>

<hr>

<s:debug/>
</body>
</html>