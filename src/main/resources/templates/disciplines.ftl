<#import "parts/common.ftl" as c>

<@c.page>
    <div>Disciplines list</div>
    <#list disciplines as discipline>
        <div>
            <b>${discipline.ID}</b>
            <span>${discipline.name}</span>
            <i>Max visitors = ${discipline.maxVisitors}</i>
        </div>
    <#else>
        <div>Disciplines not found !</div>
    </#list>
</@c.page>