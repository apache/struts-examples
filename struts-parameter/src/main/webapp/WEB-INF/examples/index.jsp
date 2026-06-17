<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Home</title>
</head>

<body>
<s:form action="index" method="POST">
    <s:iterator value="users" status="status">
        <s:hidden name="users[%{#status.index}].id"/>
        <s:textfield name="users[%{#status.index}].name" label="User %{users[#status.index].id}" id="user_%{users[#status.index].id}"/>
    </s:iterator>
    <s:textfield name="adminUser.username" label="Admin"/>
    <s:submit/>
</s:form>
</body>
</html>
