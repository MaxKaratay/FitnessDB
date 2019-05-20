<#import "parts/common.ftl" as c>
<#import  "parts/client_instructor.ftl" as ci>

<@c.page>
    <div>
        <form method="post">
            <input type="text" name="firstName" placeholder="fname"/>
            <input type="text" name="lastName" placeholder="lname"/>
            <input type="text" name="patronymic" placeholder="patro"/>
            <input type="text" name="phoneNumber" placeholder="phone">
            <input type="number" name="age" placeholder="age">
            <input type="text" name="password" placeholder="pswd">
            <button type="submit">Add Instructor</button>
        </form>
    </div>
    <div>Instructors list</div>
    <@ci.search "instructors"/>
    <#list instructors as instructor>
        <div>
            <b>${instructor.ID}</b>
            <span>${instructor.firstName}</span>
            <i>${instructor.lastName}</i>
            <span>${instructor.patronymic}</span>
            <a href="/instructors/${instructor.ID}">Edit</a>
            <a href="/instructors/delete/${instructor.ID}"
               onclick="return confirm('Do you really wont to delete ${instructor.firstName} ${instructor.lastName} ?')">Delete</a>
        </div>
    <#else>
        <div>Instructors not found !</div>
    </#list>
</@c.page>