<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
  <title>Number conversion</title>
</head>

<body>

<s:url var="themeAction" action="Theme"/>
<s:a value="%{themeAction}">Theme conversion</s:a>

<s:url var="switchToEN" action="Number">
  <s:param name="request_locale">en</s:param>
</s:url>
<s:a value="%{switchToEN}">Switch to EN</s:a>

<s:url var="switchToPL" action="Number">
  <s:param name="request_locale">pl</s:param>
</s:url>
<s:a value="%{switchToPL}">Switch to PL</s:a>

<s:url var="switchToDE" action="Number">
  <s:param name="request_locale">de</s:param>
</s:url>
<s:a value="%{switchToDE}">Switch to DE</s:a>

<s:form method="POST" action="Number">
  <table>
    <tr>
      <td>BigDecimal</td>
      <td><s:textfield name="bigDecimal"/></td>
    </tr>
    <tr>
      <td>Double</td>
      <td><s:textfield name="bigDouble"/></td>
    </tr>
    <tr>
      <td>double</td>
      <td><s:textfield name="primitiveDouble"/></td>
    </tr>
    <tr>
      <td colspan="2"><s:submit/></td>
    </tr>
  </table>
</s:form>

<pre>
  <s:text name="label"/>: <s:property value="locale"/>
</pre>
<pre>
  BigDecimal: <s:property value="bigDecimal"/>
</pre>
<pre>
  Double: <s:property value="bigDouble"/>
</pre>
<pre>
  double: <s:property value="primitiveDouble"/>
</pre>

</body>
</html>
