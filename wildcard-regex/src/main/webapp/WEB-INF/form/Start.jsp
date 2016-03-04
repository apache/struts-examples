<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
  <title>Start</title>
</head>

<body>

<s:form action="Store!store" namespace="/store">
  User name: <s:textfield name="userName"/>
  <s:submit/>
</s:form>

</body>
</html>
