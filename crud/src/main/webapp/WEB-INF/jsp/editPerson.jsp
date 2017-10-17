<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:if test="person==null || person.personId == null">
    <s:set var="title" value="%{'Add new person'}"/>
</s:if>
<s:else>
    <s:set var="title" value="%{'Update person'}"/>
</s:else>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <s:head />
        <link href="<s:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
        <style>td { white-space:nowrap; }</style>
        <title><s:property value="#title"/></title>
    </head>
    <body>
        <div class="titleDiv"><s:text name="application.title"/></div>
        <h1><s:property value="#title"/></h1>
        <s:actionerror />
        <s:actionmessage />
        <s:form action="savePerson" method="post">
            <s:textfield key="person.firstName" /> 
            <s:textfield key="person.lastName" /> 
            <s:textfield key="person.email" />
            <s:textfield key="person.phoneNumber" />
            <s:select key="person.sport" list="sports" />
            <s:radio key="person.gender" list="genders" />
            <s:select name="person.country.countryId" list="countries" listKey="countryId" listValue="countryName" label="%{getText('person.country')}"/>
            <s:checkbox key="person.over21" />
            <s:checkboxlist key="person.carModels" list="carModelsAvailable" />
            
            <s:hidden name="person.personId" value="%{person.personId}"/>

            <s:submit value="%{getText('button.label.submit')}"/>
            <s:submit value="%{getText('button.label.cancel')}" action="index"/>
        </s:form>
    </body>
</html>

