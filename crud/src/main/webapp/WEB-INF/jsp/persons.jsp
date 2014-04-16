<%-- TODO I18e all static text --%>
<%-- TODO Simplify table header texts  --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <s:head />
        <link href="<s:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
        <title><s:text name="label.persons"/></title>
    </head>
    <body>
        <div class="titleDiv"><s:text name="application.title"/></div>
        <h1><s:text name="label.persons"/></h1>
        <s:url id="url" action="inputPerson" />
        <a href="<s:property value="#url"/>">Add New Person</a>
        <br/><br/>
        <table class="borderAll">
            <tr>
                <th><s:text name="person.firstName"/></th>
                <th><s:text name="person.lastName"/></th>
                <th><s:text name="person.email"/></th>
                <th><s:text name="person.phoneNumber"/></th>
                <th><s:text name="person.sport"/></th>
                <th><s:text name="person.gender"/></th>
                <th><s:text name="person.residency"/></th>
                <th><s:text name="person.over21"/></th>
                <th><s:text name="person.carModels"/></th>
                <th>&nbsp;</th>
            </tr>
            <s:iterator value="persons" status="status">
                <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
                    <td class="nowrap"><s:property value="firstName"/></td>
                    <td class="nowrap"><s:property value="lastName"/></td>
                    <td class="nowrap"><s:property value="email"/></td>
                    <td class="nowrap"><s:property value="phoneNumber"/></td>
                    <td class="nowrap"><s:property value="sport"/></td>
                    <td class="nowrap"><s:property value="gender"/></td>
                    <td class="nowrap"><s:property value="residency"/></td>
                    <td class="nowrap"><s:property value="over21"/></td>
                    <td class="nowrap"><s:property value="carModels"/></td>
                    <td class="nowrap">
                        <s:url action="inputPerson" id="url">
                            <s:param name="person.personId" value="personId"/>
                        </s:url>
                        <a href="<s:property value="#url"/>">Edit</a>
                        &nbsp;&nbsp;&nbsp;
                        <s:url action="deletePerson" id="url">
                            <s:param name="person.personId" value="personId"/>
                        </s:url>
                        <a href="<s:property value="#url"/>">Delete</a>
                    </td>
                </tr>
            </s:iterator>
        </table>
    </body>
</html>