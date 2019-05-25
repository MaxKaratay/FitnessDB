<#import "parts/common.ftl" as c>

<style>
    fieldset{
        background: #fbffb2;
        box-sizing: border-box;
    }
</style>

<@c.page>
    <div>Add subscription</div>
    <form method="get">
        <select name="client" required>
            <option <#if _client??>value="${_client.ID}" </#if> >
                <#if _client??>
                    ${_client.firstName} ${_client.lastName} ${_client.patronymic}
                </#if>
            </option>
            <#if !_client??>
                <#list clients as c>
                <option value="${c.ID}">${c.firstName} ${c.lastName} ${c.patronymic}</option>
                </#list>
            </#if>
        </select>
        <select name="instruct" required>
            <option>
                <#if _ins??>
                    ${_ins.discipline.name} : ${_ins.instructor.firstName} ${_ins.instructor.lastName} (${_ins.price})
                </#if>
            </option>
            <#if !_ins??>
                <#list instructs?sort_by(['discipline', 'name']) as ins>
                <option value="${ins.ID}">${ins.discipline.name} : ${ins.instructor.firstName} ${ins.instructor.lastName} (${ins.price})</option>
                </#list>
            </#if>
        </select>
        <button type="submit">Ok</button>
        <div>
           <#if trainingOverflow??>
               <b style="color: red">${trainingOverflow}</b>
           </#if>
           <#if scheduleEngaged??>
               <b style="color: red">${scheduleEngaged}</b>
           </#if>
        </div>
    </form>
    <#if _client?? && _ins?? && !scheduleEngaged?? && !trainingOverflow??> <!--remove or not schedule engaged-->
        <form method="post">
            <input type="hidden" name="client" value="${_client}">
            <input type="hidden" name="instruct" value="${_ins}">
        <#list schedules as sch>
           <div>
               <label><input type="checkbox" name="${sch.ID}">${sch.period.day.name} <i>${sch.period.time.start} - ${sch.period.time.finish}</i></label>
           </div>
        </#list>
            <button type="submit">Add</button>
        </form>
    </#if>


    <!--
     -->
    <div>List of subscriptions</div><p></p>
        <div style="display: block">
        <#list subscriptions as sub>
            <div style="display: inline-block">
            <fieldset style="margin: 10px 5px 10px 10px;
                             float: left; ">
                <legend>${sub.client.firstName} ${sub.client.lastName} ${sub.client.patronymic}
                    <a href="/subscriptions/${sub.ID}">Edit</a>
                </legend>
                <span><i>Discipline:</i> ${sub.instruct.discipline.name}</span><p></p>
                <span><i>Instructor:</i> ${sub.instruct.instructor.firstName} ${sub.instruct.instructor.lastName}</span><p></p>
                <span><i>Price:</i> ${sub.instruct.price}</span><p></p>
                <span><i>End date:</i> ${sub.durationEnd}</span><p></p>
                <fieldset>
                    <legend>Visiting days</legend>
                    <#list sub.visits as v>
                    <p><span>${v.schedule.period.day.name}</span>
                        <i>${v.schedule.period.time.start} - ${v.schedule.period.time.finish}</i>
                    </p>
                    <#else>
                    Any visiting dais
                    </#list>
                </fieldset>
                <a href="/subscriptions/delete/${sub.ID}"
                   onclick="return confirm('A you realy wont to delete this subscription ?')">Delete</a>
            </fieldset>
            </div>
        <#else>
            Subscriptions not found
        </#list>
        </div>
</@c.page>