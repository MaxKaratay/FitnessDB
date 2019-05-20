<#import "parts/common.ftl" as c>

<style>
    fieldset{
        background: #d8eeff;
    }
</style>

<@c.page>
<div>
    <form method="post">
        <select name="discipline" required>
            <option></option>
            <#list disciplines as dis>
                <option>${dis.name}</option>
            </#list>
        </select>
        <select name="instructor" required>
            <option></option>
            <#list instructors as dis, instructor>
                <optgroup label="${dis.name}">
                    <#list instructor as ins, tmp>
                        <option>${ins.firstName} ${ins.lastName} ${ins.patronymic}</option>
                    </#list>
                </optgroup>
            </#list>
        </select>
        <select name="time" required>
            <option></option>
            <#list times as time>
                <option>${time.start} - ${time.finish}</option>
            </#list>
        </select>
        <button type="submit">Add</button>
        <#if alert??><b style="color: red">${alert}</b></#if>
    </form>
</div>
<!--      -->
<div style="float: left">
    <fieldset>
        <legend><b>${day.name}</b></legend>
        <#list schedule as s>
            <span>${s.instruct.discipline.name}</span>
            <span>${s.instruct.instructor.firstName} ${s.instruct.instructor.lastName}</span>
            <i>${s.period.time.start} <b>-</b> ${s.period.time.finish}</i>
            <a href="/schedules/${day.ID}/delete/${s.ID}"
               onclick="return confirm('Do you realy wont to delete this schdule ?')">Delete</a>
            <p></p>
        </#list>
    </fieldset>
    <button type="button"><a href="/schedules/${day.ID}/return">Ok</a></button>
</div>
</@c.page>