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

    <div>Clients list</div>
    <@ci.search "clients"/>
    <#list clients as client>
        <div>
            <b>${client.ID}</b>
            <span>${client.firstName}</span>
            <span>${client.lastName}</span>
            <i>${client.patronymic}</i>
            <a href="/clients/${client.ID}">Edit</a>
            <a href="/clients/delete/${client.ID}"
               onclick="return confirm('Do you really wont to delete ${client.firstName} ${client.lastName} ?')">Delete</a>
        </div>
    <#else >
        <div>Clients not found !</div>
    </#list>
</@c.page>