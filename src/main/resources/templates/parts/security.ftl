<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
    roles = user.getAuthorities()
    >

    <#if roles?seq_contains('ADMIN')>
    <#assign isAdmin = true>
    <#else>
    <#assign isAdmin = false>
    </#if>

    <#if roles?seq_contains('CLIENT')>
    <#assign isClient = true>
    <#else>
    <#assign isClient = false>
    </#if>

    <#if roles?seq_contains('INSTRUCTOR')>
    <#assign isInstructor = true>
    <#else>
    <#assign isInstructor = false>
    </#if>


<#else>
<#assign
name = "unknown"
isAdmin = false
>
</#if>