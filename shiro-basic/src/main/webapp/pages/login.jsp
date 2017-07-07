<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Login Page</title>
    <link href="<s:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
</head>
<s:head theme="xhtml" />

<body>
<h3>Welcome User, please login below</h3>
<s:form action="authuser" theme="xhtml">
    <s:textfield name="username" label="User Name"></s:textfield>
    <s:textfield name="password" label="Password" type="password"></s:textfield>
    <s:submit value="Login"></s:submit>
    <s:if test="hasActionErrors()">
       <div class="errors">
          <s:actionerror/>
       </div>
    </s:if>
</s:form>
</body>
</html>