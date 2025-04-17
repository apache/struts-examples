<#assign itemCount = 0/>
<#if attributes.list??>
    <@s.iterator value="attributes.list">
        <#assign itemCount = itemCount + 1/>
        <#if attributes.listKey??>
            <#assign itemKey = stack.findValue(attributes.listKey)/>
        <#else>
            <#assign itemKey = stack.findValue('top')/>
        </#if>
        <#if attributes.listValue??>
            <#assign itemValue = stack.findString(attributes.listValue)?default("")/>
        <#else>
            <#assign itemValue = stack.findString('top')/>
        </#if>
<#assign itemKeyStr=itemKey.toString() />
<input type="checkbox" name="${attributes.name?esc}" value="${itemKeyStr?esc}" id="${attributes.name?esc}-${itemCount}"<#rt/>
        <#if tag.contains(attributes.nameValue, itemKey)>
 checked="checked"<#rt/>
        </#if>
        <#if attributes.disabled?default(false)>
 disabled="disabled"<#rt/>
        </#if>
        <#if attributes.title??>
 title="${attributes.title?esc}"<#rt/>
        </#if>
        <#include "/${attributes.templateDir}/simple/scripting-events.ftl" />
        <#include "/${attributes.templateDir}/simple/common-attributes.ftl" />
/>
<label for="${attributes.name?esc}-${itemCount}" style="color:red;font-weight:bold">${itemValue?esc}</label> <br />
    </@s.iterator>
<#else>
  &nbsp;
</#if>
<input type="hidden" id="__multiselect_${attributes.id?esc}" name="__multiselect_${attributes.name?esc}" value=""<#rt/>
<#if attributes.disabled?default(false)>
 disabled="disabled"<#rt/>
</#if>
 /> 