<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>File upload: result</title>
</head>

<body>

<p>
<s:a action="input">back to input</s:a>
</p>

<s:actionerror/>

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
