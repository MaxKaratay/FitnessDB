<#macro search path>
    <form method="get" action=${path}>
        <input type="text" name="firstNameFilter" value="${firstNameFilter!""}" placeholder="first name"/>
        <input type="text" name="lastNameFilter" value="${lastNameFilter!""}" placeholder="last name"/>
        <button type="submit">Search</button>
    </form>
</#macro>