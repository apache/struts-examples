<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>SiteMesh example: error</title>
</head>
<body>
<h2>SiteMesh example: error</h2>
<s:actionerror/>

<s:url var="url" action="hello">
    <s:param name="decorator" value="1"/>
</s:url>
<s:a href="%{url}">Hello</s:a>
</body>
</html>
