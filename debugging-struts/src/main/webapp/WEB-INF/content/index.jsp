<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Debugging Struts 2 - Welcome</title>
    <s:head/>
</head>
<body>
<h1>Welcome To Struts 2!</h1>

<p><s:a href="hello">Hello World</s:a></p>
<s:url action="hello" var="helloLink">
    <s:param name="userName">Bruce Phillips</s:param>
</s:url>
<p><a href="${helloLink}">Hello Bruce Phillips</a></p>

<p>Get your own personal hello by filling out and submitting this form.</p>

<s:form action="hello">
    <s:textfield name="userName" label="Your name"/>
    <s:submit value="Submit"/>
</s:form>

<p><s:a action="register-input">Please register</s:a> for our prize drawing.</p>
<p><s:a action="causeexception">Cause Exception</s:a></p>
<p><s:a action="causenullpointerexception">Cause Null Pointer Exception</s:a></p>
<p><s:a action="causesecurityexception">Cause Global Security Exception</s:a></p>
<p><s:a action="actionspecificexception">Cause ActionSpecific Security Exception</s:a></p>

<hr/>

<h3>Debugging</h3>

<p><s:a action="index" namespace="config-browser">Launch the configuration browser</s:a></p>
<s:url action="index" var="indexLink">
    <s:param name="debug">browser</s:param>
</s:url>
<p><s:a href="%{indexLink}">Reload this page with debugging</s:a></p>

<s:debug/>
</body>
</html>