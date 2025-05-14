<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>File upload: result</title>
</head>

<body>

<s:form action="upload" method="post" enctype="multipart/form-data">
    <s:file name="upload"/>
    <s:file name="upload"/>
    <s:file name="upload"/>
    <s:submit/>
</s:form>

<s:actionerror/>

<s:iterator value="upload" var="u">
    File: <s:property value="u"/><br/>
</s:iterator>
<s:iterator value="uploadContentType" var="ct">
    Content-Type: <s:property value="ct"/><br/>
</s:iterator>
<s:iterator value="uploadFileName" var="fn">
    File name: <s:property value="fn"/><br/>
</s:iterator>

</body>
</html>
