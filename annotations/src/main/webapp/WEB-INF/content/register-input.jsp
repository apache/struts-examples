<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Basic Struts 2 Application - Register</title>
</head>

<body>
<h3>Register for a prize by completing this form.</h3>

<s:form action="register">

    <s:textfield name="personBean.firstName" label="First name"/>
    <s:textfield name="personBean.lastName" label="Last name"/>
    <s:textfield name="personBean.email" label="Email"/>
    <s:textfield name="personBean.age" label="Age"/>

    <s:submit/>

</s:form>

</body>
</html>