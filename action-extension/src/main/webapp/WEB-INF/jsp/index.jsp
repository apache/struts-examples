<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Action Extension - Welcome</title>
</head>
<body>
<h1>Action extensions in Apache Struts</h1>
<p>An action extension can be set in three places. Struts uses the most specific one: the action, then its package, then parent packages, then the global <code>struts.action.extension</code> constant. See <code>struts.xml</code>.</p>
<p>The wrong-extension links are plain <code>&lt;a href&gt;</code> links, because <code>&lt;s:url&gt;</code> would always fix the extension.</p>
<ul>
    <li><code>orders.action</code> and <code>report.do</code> reach an action that declares its own extensions, so the <code>actionExtension</code> interceptor rejects them and shows the <code>bad-extension</code> result.</li>
    <li><code>hello.do</code> is a plain 404. <code>hello</code> declares no extension, so only the global list (<code>action</code>) applies to it, and <code>.do</code> is never mapped to it.</li>
</ul>
<%@ include file="links.jspf" %>
<p><a href="<s:url action='index' namespace='/'/>">Back to the start page</a></p>
</body>
</html>
