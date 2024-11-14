<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Basic Struts 2 Application - Welcome</title>
    <s:script type="text/javascript">
      function openPopup() {
        window.open('hello', 'myTarget', 'width=300,height=200,scrollbars=yes');
      }
    </s:script>
  </head>

  <body>
    <h1>Welcome To Struts 2!</h1>
    <p>
      <a href="<s:url namespace="" action='hello'/>">Hello World</a>
    </p>

    <p>
      <button onclick="openPopup()">Open popup</button>
    </p>

    <s:url namespace="" action="hello" var="helloLink">
      <s:param name="userName">Bruce Phillips</s:param>
    </s:url>
    <p><a href="${helloLink}">Hello Bruce Phillips</a></p>

    <p>Get your own personal hello by filling out and submitting this form.</p>
    <s:form namespace="" action="hello">
      <s:textfield name="userName" label="%{'Your name'}"/>
      <s:submit value="%{'Submit'}"/>
    </s:form>
  </body>
</html>
