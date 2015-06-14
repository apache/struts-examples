<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Debugging Struts 2 - Welcome</title>
    <s:head/>
</head>
<body>
    <h1>Welcome To Struts 2!</h1>

    <p><a href="<s:url action='hello'/>">Hello World</a></p>
    <s:url action="hello" var="helloLink">
        <s:param name="userName">Bruce Phillips</s:param>
    </s:url>
    <p><a href="${helloLink}">Hello Bruce Phillips</a></p>

    <p>Get your own personal hello by filling out and submitting this form.</p>

    <s:form action="hello">
        <s:textfield name="userName" label="Your name"/>
        <s:submit value="Submit"/>
    </s:form>

    <p><a href="register.jsp">Please register</a> for our prize drawing.</p>
    <p><a href='<s:url action="causeexception" />'>Cause Exception</a></p>
    <p><a href='<s:url action="causenullpointerexception" />'>Cause Null Pointer Exception</a></p>
    <p><a href='<s:url action="causesecurityexception" />'>Cause Global Security Exception</a></p>
    <p><a href='<s:url action="actionspecificexception" />'>Cause ActionSpecific Security Exception</a></p>

    <hr/>

    <h3>Debugging</h3>

    <p><a href="<s:url action="index" namespace="config-browser" />">Launch the configuration browser</a></p>
    <s:url action="index" var="indexLink">
        <s:param name="debug">browser</s:param>
    </s:url>
    <p><a href="${indexLink}">Reload this page with debugging</a></p>

    <s:debug/>
</body>
</html>