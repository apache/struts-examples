<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Welcome Page</title>
    <link href="<s:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
</head>
<s:head theme="xhtml" />

<body>
  <shiro:authenticated>
    <h3>Welcome <s:property value="username"></s:property></h3>
    <h4>Roles:</h4>
    <ul>
        <shiro:hasRole name="schwartz"><li>May the Schwartz be with you!</shiro:hasRole>
        <shiro:lacksRole name="schwartz"><li>Hello, mere mortal.</shiro:lacksRole>
    </ul>
    <h4>Permissions:</h4>
    <ul>
        <shiro:hasPermission name="lightsaber:weild"><li>You may use a lightsaber ring.  Use it wisely.<br></shiro:hasPermission>
        <shiro:lacksPermission name="lightsaber:weild"><li>Sorry, lightsaber rings are for schwartz masters only.</shiro:lacksPermission>
        <shiro:hasPermission name="winnebago:drive:eagle5"><li>You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.<br>Here are the keys - have fun!<br></shiro:hasPermission>
        <shiro:lacksPermission name="winnebago:drive:eagle5"><li>Sorry, you aren't allowed to drive the 'eagle5' winnebago!</shiro:lacksPermission>
    </ul>
  </shiro:authenticated>
  <shiro:notAuthenticated>
    <h3>Welcome Guest</h3>
  </shiro:notAuthenticated>

  <br>
  <s:form action="logout">
    <s:submit value="Logout"></s:submit>
  </s:form>
</body>
</html>