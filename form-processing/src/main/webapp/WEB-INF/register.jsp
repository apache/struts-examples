<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Register</title>
</head>
<body>
<h3>Register for a prize by completing this form.</h3>

<s:form action="register-submit">
    <s:textfield name="personBean.firstName" label="First name"/>
    <s:textfield name="personBean.lastName" label="Last name"/>
    <s:textfield name="personBean.email" label="Email"/>
    <s:textfield name="personBean.age" label="Age"/>
    <s:submit/>
    <s:submit action="register-cancel" value="Cancel" />
</s:form>
</body>
</html>
