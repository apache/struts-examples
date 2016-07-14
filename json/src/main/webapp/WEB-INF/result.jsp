<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
  <title>JSON Result</title>
</head>

<body>
<ul>
  <li>
    counter: <s:property value="bean.counter"/>
  </li>
  <s:iterator value="bean.names" var="name" status="idx">
  <li>
    name<s:property value="#idx"/>: <s:property value="#name"/>
  </li>
  </s:iterator>
</ul>

</body>
</html>
