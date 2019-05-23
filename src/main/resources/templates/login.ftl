<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Fitness Center</title>

</head>
<body style="background: #dcffe7">

<div style="float: left" >
    <fieldset>
        <legend>Login</legend>
        <form action="/login" method="post" >
            <div><label> First and Last name with Patronymic: <input type="text" name="username"/> </label></div>
            <div><label> Password: <input type="password" name="password"/> </label></div>
            <div><input type="submit" value="Sign In"/></div>
        </form>
    </fieldset>

<#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
    <div>
        <label style="color: red">Error: ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}</label>
    </div>
</#if>
</div>

</body>
</html>