<#assign itemCount = 0/>
<#if parameters.list??>
    <@s.iterator value="parameters.list">
        <#assign itemCount = itemCount + 1/>
        <#if parameters.listKey??>
            <#assign itemKey = stack.findValue(parameters.listKey)/>
        <#else>
            <#assign itemKey = stack.findValue('top')/>
        </#if>
        <#if parameters.listValue??>
            <#assign itemValue = stack.findString(parameters.listValue)?default("")/>
        <#else>
            <#assign itemValue = stack.findString('top')/>
        </#if>
<#assign itemKeyStr=itemKey.toString() />
<input type="checkbox" name="${parameters.name?esc}" value="${itemKeyStr?esc}" id="${parameters.name?esc}-${itemCount}"<#rt/>
        <#if tag.contains(parameters.nameValue, itemKey)>
 checked="checked"<#rt/>
        </#if>
        <#if parameters.disabled?default(false)>
 disabled="disabled"<#rt/>
        </#if>
        <#if parameters.title??>
 title="${parameters.title?esc}"<#rt/>
        </#if>
        <#include "/${parameters.templateDir}/simple/scripting-events.ftl" />
        <#include "/${parameters.templateDir}/simple/common-attributes.ftl" />
/>
<label for="${parameters.name?esc}-${itemCount}" style="color:red;font-weight:bold">${itemValue?esc}</label> <br />
    </@s.iterator>
<#else>
  &nbsp;
</#if>
<input type="hidden" id="__multiselect_${parameters.id?esc}" name="__multiselect_${parameters.name?esc}" value=""<#rt/>
<#if parameters.disabled?default(false)>
 disabled="disabled"<#rt/>
</#if>
 /> 