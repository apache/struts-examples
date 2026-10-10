<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
  <title><s:text name="default.index.title"/></title>
</head>

<body>

<h1>
  <s:text name="default.index.title"/>
</h1>

<h2>
  <s:text name="global.key"/>
</h2>

<h3>Bundles registered with a wildcard</h3>
<ul>
  <li><s:text name="common.greeting"/></li>
  <li><s:text name="labels.title"/></li>
  <li><s:text name="shared.key"/></li>
</ul>

<h3>Select your provider</h3>
<ul>
  <li>
    <s:url var="url" action="index"/>
    <s:a href="%{url}">Default aka scan everything</s:a>
  </li>
  <li>
    <s:url var="url" action="system"/>
    <s:a href="%{url}">System aka use only default bundles</s:a>
  </li>
  <li>
    <s:url var="url" action="factory"/>
    <s:a href="%{url}">Factory aka use custom TextProviderFactory</s:a>
  </li>
</ul>

</body>
</html>
