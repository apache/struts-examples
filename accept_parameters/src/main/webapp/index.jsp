<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Basic Struts 2 Application - Welcome</title>
</head>
<body>
<h1>Welcome To Struts 2!</h1>
<li>
  <s:url namespace="/accept" action="a1" var="url" escapeAmp="false">
    <s:param name="para1" value="'v1'"/>
    <s:param name="para2" value="'v2'"/>
  </s:url>
  <a href="<s:property value="%{url}"/>">Action 1</a> - only para1 is accepted
</li>
<li>
  <s:url namespace="/accept" action="a2" var="url" escapeAmp="false">
    <s:param name="para1" value="'v1'"/>
    <s:param name="para2" value="'v2'"/>
  </s:url>
  <a href="<s:property value="%{url}"/>">Action 2</a> - all parameters are accepted
</li>
<li>
  <s:url namespace="/accept" action="a3" var="url" escapeAmp="false">
    <s:param name="para1" value="'v1'"/>
    <s:param name="para2" value="'v2'"/>
  </s:url>
  <a href="<s:property value="%{url}"/>">Action 3</a> - no parameter is accepted
</li>
</body>
</html>