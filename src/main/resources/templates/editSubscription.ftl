<#import "parts/common.ftl" as c>

<style>
    fieldset{
        background: #fbffb2;
    }
</style>

<@c.page>
    <fieldset style="float: left">
        <legend>${subscription.client.firstName} ${subscription.client.lastName} ${subscription.client.patronymic}</legend>
        <form method="post">
            <span><i>Discipline:</i> ${subscription.instruct.discipline.name}</span><p></p>
            <i>Instructor:</i> <span>${subscription.instruct.instructor.firstName} ${subscription.instruct.instructor.lastName}</span><p></p>
            <span><i>Price:</i> ${subscription.instruct.price}</span><p></p>
            <span><i>End date:</i> ${subscription.durationEnd}</span><p></p>
            <fieldset>
                <legend>Visiting days</legend>
                    <#list allSchedule?sort_by(['period','day', 'ID']) as s>
                    <div>
                        <label>
                            <input type="checkbox" name="${s.ID}" ${scheduleByClient?seq_contains(s)?string("checked","")}>
                            <span>${s.period.day.name}</span>
                            <i>${s.period.time.start} - ${s.period.time.finish}</i>
                        </label>
                    </div>
                    <#else>
                    Any visiting dais
                    </#list>
            </fieldset>
            <button type="submit">Save</button>
        </form>
    </fieldset>
</@c.page>