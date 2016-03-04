<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:actionerror/>

<s:form action="login-submit" method="POST" class="form-horizontal col-sm-4">
  <s:textfield name="email" label="E-mail"/>
  <s:password name="password" label="Password" />
  <s:submit label="Login" class="btn btn-default col-sm-offset-3"/>
</s:form>
