<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Registration Successful</title>
</head>
<body>
<h3><s:text name="thankyou" /></h3>

<p>Your registration information: <s:property value="personBean" /> </p>

<p><a href="<s:url action='index' />" >Return to home page</a>.</p>
<hr />
<s:text name="contact" />
</body>
</html>