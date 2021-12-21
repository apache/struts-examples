<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Dynamic url</title>
</head>

<body>

<s:url var="delUrl" action="delete">
  <s:param name="id" value="#entityId" />
</s:url>

<s:a id="id_%{#attr.processId}" href="%{#attr.delUrl}" escapeHtmlBody="false">
  <img src='<s:url value="/images/icon_waste_sml.png"/>' alt="X" title="<s:property value='#attr.deleteItem' />" />
</s:a>

</body>
</html>
