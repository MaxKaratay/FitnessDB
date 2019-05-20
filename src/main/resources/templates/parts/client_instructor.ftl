<#macro search path>
    <form method="get" action=${path}>
        <input type="text" name="firstNameFilter" value="${firstNameFilter!""}"/>
        <input type="text" name="lastNameFilter" value="${lastNameFilter!""}"/>
        <button type="submit">Search</button>
    </form>
</#macro>