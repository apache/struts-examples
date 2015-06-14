<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Debugging Struts 2 - Register</title>
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

	<hr>

	<s:debug/>
</body>
</html>