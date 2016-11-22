<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>File upload</title>
</head>

<body>

<s:form action="upload" method="post" enctype="multipart/form-data">
    <s:file name="upload"/>
    <s:file name="upload"/>
    <s:file name="upload"/>
    <s:submit/>
</s:form>

<s:iterator value="upload" var="u">
    <s:property value="u"/><br/>
</s:iterator>
<s:iterator value="uploadContentType" var="ct">
    <s:property value="ct"/><br/>
</s:iterator>
<s:iterator value="uploadFileName" var="fn">
    <s:property value="fn"/><br/>
</s:iterator>

</body>
</html>
