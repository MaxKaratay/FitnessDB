<#import "parts/common.ftl" as c>

<@c.page>
    <form action="/clients/${client.ID}" method="post">
        <div><input type="text" value="${client.firstName}" name="firstName" placeholder="fname"></div>
        <div><input type="text" value="${client.lastName}" name="lastName" placeholder="lname"/></div>
        <div><input type="text" value="${client.patronymic}" name="patronymic" placeholder="patro"/></div>
        <div><input type="text" value="${client.phoneNumber}" name="phoneNumber" placeholder="phone"></div>
        <div><input type="number" value="${client.age}" name="age" placeholder="age"></div>
        <div><input type="hidden" value="${client.ID}" name="clientID"></div>
        <div>
            <button type="submit">Change</button>
        </div>
    </form>
</@c.page>