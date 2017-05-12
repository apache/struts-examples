<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Theme selector</title>
</head>

<body>

<s:url var="numberAction" action="Number"/>
<s:a value="%{numberAction}">Number conversion</s:a>

<s:form method="POST" action="design" theme="simple">
  <table>
    <tr>
      <td>Choose</td>
      <td><s:select name="selectedTheme" emptyOption="true" list="themes" listKey="value" listValue="value.displayName"/></td>
      <td><s:submit/></td>
    </tr>
  </table>
</s:form>

<pre>
  <s:property value="selectedTheme.toString"/>
</pre>

</body>
</html>
