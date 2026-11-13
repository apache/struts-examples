<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Action Extension - Wrong extension</title>
</head>
<body>
<h1>Wrong extension</h1>
<p>The request to <code><s:property value="#request['jakarta.servlet.forward.request_uri']"/></code> was rejected
    by the <code>actionExtension</code> interceptor. The action exists, but its declared extensions do not include the one in this URL.</p>
<p>The interceptor returned the <code>bad-extension</code> result, which this example maps to this page. Without that result, the response would be a 404.</p>
<p><a href="<s:url action='index' namespace='/'/>">Back to the start page</a></p>
</body>
</html>
