<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>SiteMesh example: Admin Index</title>
</head>
<body>
<h2>SiteMesh example: Admin Index with Default Decorator</h2>
<s:url var="url" action="hello" namespace="/">
    <s:param name="decorator" value="1"/>
</s:url>
<s:a href="%{url}">Hello</s:a>
</body>
</html>