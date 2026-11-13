<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Action Extension - Action</title>
</head>
<body>
<h1>Action extension</h1>
<p>You reached <code>report</code> in the <code>legacy</code> package. The action declares <code>extension=""</code>, which overrides the package's <code>do</code>. An empty entry means no extension, so the URL is <code>/legacy/report</code>.</p>
<%@ include file="links.jspf" %>
<p><a href="<s:url action='index' namespace='/'/>">Back to the start page</a></p>
</body>
</html>
