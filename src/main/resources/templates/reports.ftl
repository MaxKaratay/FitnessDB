<#import "parts/common.ftl" as c>

<style>
    table {
        border-collapse: collapse;
    }

    th, td {
        padding: 3px;
        border: 1px solid black;
    }
</style>

<@c.page>
<div style="margin-bottom: 5px">
    Activity result
</div>
<div>
    <table>
        <tr>
            <th scope="col">Discipline</th>
            <th colspan="3">Instructor</th>
            <th scope="col">Number of Clients</th>
            <th scope="col">Profit</th>
        </tr>
        <#list instructs as dis, ins>
                <#list ins as i, num>
                <tr>
                     <td scope="rowgroup">${dis.name}</td>
                     <td colspan="3"
                         scope="row">${i.instructor.firstName} ${i.instructor.lastName} ${i.instructor.patronymic}</td>
                     <td style="text-align: center">${num}</td>
                     <td style="text-align: right">${num * i.price}</td>
                </tr>
                </#list >
        </#list>
    </table>
</div>
<div style="margin: 10px">
    <label><b style="color: coral">Profit in current month : </b> ${profit}</label>
</div>
</@c.page>