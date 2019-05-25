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
    <div style="float: left; margin-left: 15px">
        <#list instructs as discipline, instructors>
            <fieldset>
            <legend><b>${discipline.name}</b>  <#if isAdmin> <a href="/instructs/${discipline.ID}">Edit</a> </#if></legend>
                <table style="border-spacing: 5px">
                    <#list instructors as instructor, price>
                        <tr class="divv">
                            <td><span>${instructor.firstName}</span></td>
                            <td><i>${instructor.lastName}</i></td>
                            <td><span>${instructor.patronymic}</span></td>
                            <td><b><i>with price: ${price}</i></b></td>
                        </tr>
                    <#else>
                        <div>None</div>
                    </#list>
                </table>
            </fieldset>
        <#else>
            <div>Not found</div>
        </#list>
    </div>
</@c.page>