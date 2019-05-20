<#import "parts/common.ftl" as c>

<style>
    fieldset{
        background: #fff4fd;
        margin-bottom: 5px;
    }
    div{
        margin-bottom: 10px;
    }
</style>

<@c.page>
    <div style="float: left">
        <form action="/instructs/${discipline.ID}" method="post">
         <fieldset>
         <legend><b>${discipline.name}</b></legend>
        <#list ins_price as instructor, price>
        <div>
            <label>${instructor.firstName} ${instructor.lastName} ${instructor.patronymic}</label>
            <input type="number" name="${instructor.firstName} ${instructor.lastName} ${instructor.patronymic}" value="${price}">
            <button type="submit">Change</button>
            <a href="/instructs/${discipline.ID}/delete/${instructor.ID}"
               onclick="return confirm('Do you really wont to delete ${instructor.firstName} ${instructor.lastName} ${instructor.patronymic} ?')" >Delete</a>
        </div>
        <#else>
            No one instruct it
        </#list>
         </fieldset>
        </form>
    </div>
</@c.page>