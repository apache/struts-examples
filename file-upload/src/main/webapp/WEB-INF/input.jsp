<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>File upload: input</title>
</head>

<body>

<s:form action="upload" method="post" enctype="multipart/form-data">
    <s:file name="upload"/>
    <s:file name="upload"/>
    <s:file name="upload"/>
    <s:submit/>
</s:form>

<s:actionerror/>

</body>
</html>
