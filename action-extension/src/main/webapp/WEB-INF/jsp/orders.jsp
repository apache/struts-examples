<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Action Extension - Package</title>
</head>
<body>
<h1>Package extension</h1>
<p>You reached <code>orders</code> in the <code>legacy</code> package. The package declares <code>extension="do"</code>, so every action in it answers on <code>.do</code> unless the action declares its own.</p>
<%@ include file="links.jspf" %>
<p><a href="<s:url action='index' namespace='/'/>">Back to the start page</a></p>
</body>
</html>
