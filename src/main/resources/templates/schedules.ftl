<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<style>
    fieldset{
        background: #d8eeff;
    }
</style>

<@c.page>
<div>Club shedules</div>
    <div>
        <#list days as day, schedule>
            <div style="float: left; margin: 20px">
                <fieldset>
                    <legend><b>${day.name}</b>  <#if isAdmin><a href="/schedules/${day.ID}">Edit</a></#if></legend>
                    <#list schedule as s>
                        <i>${s.ID}</i>
                        <span>${s.instruct.discipline.name}</span>
                        <span>${s.instruct.instructor.firstName} ${s.instruct.instructor.lastName}</span>
                        <i>${s.period.time.start} <b>-</b> ${s.period.time.finish}</i>
                        <p></p>
                    </#list>
                </fieldset>
            </div>
        <#else>
            Schedules not found
        </#list>
    </div>
</@c.page>