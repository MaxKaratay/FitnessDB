<#import "parts/common.ftl" as c>

<@c.page>
    <form action="/instructors/${instructor.ID}" method="post">
        <div><input type="text" value="${instructor.firstName}" name="firstName" placeholder="fname"></div>
        <div><input type="text" value="${instructor.lastName}" name="lastName" placeholder="lname"/></div>
        <div><input type="text" value="${instructor.patronymic}" name="patronymic" placeholder="patro"/></div>
        <div><input type="text" value="${instructor.phoneNumber}" name="phoneNumber" placeholder="phone"></div>
        <div><input type="number" value="${instructor.age}" name="age" placeholder="age"></div>
        <div><input type="hidden" value="${instructor.ID}" name="instructorID"></div>
        <div>
            <button type="submit">Change</button>
        </div>
    </form>
</@c.page>