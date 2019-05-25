<#import "parts/common.ftl" as c>
<#import "parts/client_instructor.ftl" as ci>

<@c.page>
    <div>
        <form method="post">
            <input type="text" name="firstName" placeholder="fname"/>
            <input type="text" name="lastName" placeholder="lname"/>
            <input type="text" name="patronymic" placeholder="patro"/>
            <input type="text" name="phoneNumber" placeholder="phone">
            <input type="number" name="age" placeholder="age">
            <input type="text" name="password" placeholder="pswd">
            <button type="submit">Add Client</button>
        </form>
    </div>
    <p></p>
    <div>Clients list</div>
    <@ci.search "clients"/>
    <p></p>
    <table style="border-spacing: 5px">
    <#list clients?sort_by('firstName') as client>
        <tr>
            <td><span>${client.firstName}</span></td>
            <td><span>${client.lastName}</span></td>
            <td><i>${client.patronymic}</i></td>
            <td><a href="/clients/${client.ID}">Edit</a></td>
            <td>&nbsp;</td>
            <td><a href="/clients/delete/${client.ID}"
                   onclick="return confirm('Do you really wont to delete ${client.firstName} ${client.lastName} ?')">Delete</a>
            </td>
        </tr>
    <#else >
        <div>Clients not found !</div>
    </#list>
    </table>
</@c.page>