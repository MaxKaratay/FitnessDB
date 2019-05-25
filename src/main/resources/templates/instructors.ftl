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
    <p></p>
    <div>Instructors list</div>
    <@ci.search "instructors"/>
    <p></p>
    <table style="border-spacing: 5px">
    <#list instructors?sort_by("firstName") as instructor>
        <tr>
            <td><span>${instructor.firstName}</span></td>
            <td><i>${instructor.lastName}</i></td>
            <td><span>${instructor.patronymic}</span></td>
            <td><a href="/instructors/${instructor.ID}">Edit</a></td>
            <td>&nbsp;</td>
            <td><a href="/instructors/delete/${instructor.ID}"
                   onclick="return confirm('Do you really wont to delete ${instructor.firstName} ${instructor.lastName} ?')">Delete</a>
            </td>
        </tr>
    <#else>
        <div>Instructors not found !</div>
    </#list>
    </table>
</@c.page>