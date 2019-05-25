<#import "parts/common.ftl" as c>

<@c.page>
    <div><h3>Hello, ${Session.SPRING_SECURITY_CONTEXT.authentication.principal.getUsername()} !</h3></div>
    <#if subs??>
        <div> All yours active subscriptions</div>
        <div style="display: block; margin-left: 20px">
        <#list subs as sub>
            <div style="display: inline-block">
            <fieldset style="float: left; margin: 10px; background: #fbffb2">
                <span><i>Discipline:</i> ${sub.instruct.discipline.name}</span>
                <p></p>
                <span><i>Instructor:</i> ${sub.instruct.instructor.firstName} ${sub.instruct.instructor.lastName}</span>
                <p></p>
                <span><i>Price:</i> ${sub.instruct.price}</span>
                <p></p>
                <span><i>End date:</i> ${sub.durationEnd}</span>
                <p></p>
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
            </fieldset>
            </div>
        <#else>
            No subscriptions for you !
        </#list>
        </div>
    </#if>

    <!--
     -->

    <#if schedules??>
    <h4>Yours work periods :</h4>
    <div style="display: block; margin-left: 20px">
        <#list schedules as s, n>
            <div style="display: inline-block">
                <fieldset style="float: left; margin: 5px; background: #d8eeff">
                    <div><i>${s.period.day.name}</i></div>
                    <div>
                        <span>${s.instruct.discipline.name}</span>
                        <i>${s.period.time.start} <b>-</b> ${s.period.time.finish}</i>
                    </div>
                    <span> Number of clients ${n}</span>
                </fieldset>
            </div>
        <#else>
            Schedules not found!
        </#list>
    </div>
    </#if>
</@c.page>

