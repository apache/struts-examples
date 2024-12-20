<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>SiteMesh example: Hello 2 with Decorator 2</title>
</head>
<body>
<h2>SiteMesh example: Hello 2 with Decorator 2</h1>
<h3>Decorators</h3>
<div>
    <s:form action="hello" method="get">
        <s:select name="decorator" list="decorators"/>
        <s:submit value="Decorate"/>
    </s:form>
</div>
<p>Selected decorator: <s:property value="decorator"/></p>
</body>
</html>