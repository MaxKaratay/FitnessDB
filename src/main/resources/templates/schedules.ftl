<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<style>
    fieldset {
        background: #d8eeff;
    }
</style>

<@c.page>
<div>Club shedules</div>
    <div>
        <#list days as day, schedule>
            <div style="float: left; margin: 15px">
                <fieldset>
                    <legend><b>${day.name}</b>  <#if isAdmin><a href="/schedules/${day.ID}">Edit</a></#if></legend>
                    <table style="border-spacing: 7px">
                    <#list schedule as s>
                        <tr>
                            <td><i>${s.period.time.start} <b>-</b> ${s.period.time.finish}</i></td>
                            <td><span>${s.instruct.discipline.name}</span></td>
                            <td><span>${s.instruct.instructor.firstName} ${s.instruct.instructor.lastName}</span></td>
                            <td><p></p></td>
                        </tr>
                    </#list>
                    </table>
                </fieldset>
            </div>
        <#else>
            Schedules not found
        </#list>
    </div>
</@c.page>