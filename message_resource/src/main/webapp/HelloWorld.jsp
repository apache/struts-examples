<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello World!</title>
</head>
<body>
<h1><s:text name="greeting" /></h1>
<h2><s:property value="messageStore.message" /></h2>
<p>I've said hello <s:property value="helloCount" /> times!</p>
<p><s:property value="messageStore" /></p>
<p><a href="<s:url action='index' />" >Return to home page</a>.</p>
<hr />
<s:text name="contact" />
</body>
</html>