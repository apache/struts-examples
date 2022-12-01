<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Refresh every 5 seconds -->
    <meta http-equiv="refresh" content="5;url=<s:url includeParams="all"/>"/>
    <title>Execute And Wait Test</title>
</head>

<body>
<p>
    Waiting...
</p>
<p>
    Refresh URL (every 3 seconds): <!--s:url includeParams="all"/-->
</p>
</body>
</html>
