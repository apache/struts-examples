<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Struts 2 Form Tags - Edit Person</title>

</head>
<body>
<h1>Update Information</h1>

<p>Use the form below to edit your information.</p>

<s:form action="save" method="post">
<s:textfield key="personBean.firstName" />
<s:textfield key="personBean.lastName" />
<s:select key="personBean.sport" list="sports" />
<s:radio key="personBean.gender" list="genders" />
<s:select key="personBean.residency" list="states" listKey="stateAbbr" listValue="stateName" />
<s:checkbox key="personBean.over21" />
<s:checkboxlist key="personBean.carModels" list="carModelsAvailable" />
<s:submit key="submit" />
</s:form>

</body>
</html>