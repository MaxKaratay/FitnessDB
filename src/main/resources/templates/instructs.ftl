<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<style>
    fieldset{
        background: #fff4fd;
        margin-bottom: 5px;
    }

    .divv{
        margin-bottom: 10px;
    }
</style>

<@c.page>
    <#if isAdmin>
        <form method="post">
            <select name="discipline" required>
                <option></option>
        <#list disciplines as discipline>
            <option>${discipline.name}</option>
        </#list>
            </select>
            <select name="instructor" required>
                <option></option>
        <#list instructors as instructor>
            <option>${instructor.firstName} ${instructor.lastName} ${instructor.patronymic}</option>
        </#list>
            </select>
            <input type="number" name="price" placeholder="price">
            <button type="submit">Add</button>
        </form>
    </#if>

    <div style="margin-bottom: 5px">List of discipline and instructors</div>
    <div style="float: left">
        <#list instructs as discipline, instructors>
            <fieldset>
            <legend><b>${discipline.name}</b>  <#if isAdmin> <a href="/instructs/${discipline.ID}">Edit</a> </#if></legend>
                <div >
                    <#list instructors as instructor, price>
                        <div class="divv">
                            <span>${instructor.firstName}</span>
                            <i>${instructor.lastName}</i>
                            <span>${instructor.patronymic}</span>
                            <b><i>with price: ${price}</i></b>
                        </div>
                    <#else>
                        <div>None</div>
                    </#list>
                </div>
            </fieldset>
        <#else>
            <div>Not found</div>
        </#list>
    </div>
</@c.page>