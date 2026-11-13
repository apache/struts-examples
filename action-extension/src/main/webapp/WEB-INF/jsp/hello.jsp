<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Action Extension - Global</title>
</head>
<body>
<h1>Global extension</h1>
<p>You reached <code>hello</code> in the <code>default</code> package. Neither the action nor its package declares an extension, so it answers on the global <code>struts.action.extension</code> value: <code>.action</code>.</p>
<%@ include file="links.jspf" %>
<p><a href="<s:url action='index' namespace='/'/>">Back to the start page</a></p>
</body>
</html>
