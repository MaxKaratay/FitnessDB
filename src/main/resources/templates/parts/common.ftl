<#include "security.ftl">

<#macro page>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <title>FitDB</title>
    <meta charset=UTF-8" name="viewport" content="width=device-width, initial-scale=1"/>
    <style>
        body{
            margin: 0;
            background: #dcffe7;
        }
        .topnav {
            overflow: hidden;
            background-color: #333;
            margin-bottom: 5px;
        }
        .topnav a {
            float: left;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }
        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }
        .topnav a.active {
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body>
<div class="topnav">
    <a class="active" href="/">Home</a>
    <#if isAdmin><a href="/clients"> Clients list</a></#if>
    <#if isAdmin><a href="/instructors"> Instructors list</a></#if>
    <a href="/disciplines"> Disciplines list</a>
    <a href="/instructs"> Instruct </a>
    <a href="/schedules"> Schedules </a>
    <#if isAdmin><a href="/subscriptions">Subscriptions</a></#if>
    <#if isAdmin> <a href="/reports"> Report </a></#if>
    <div style="float: right;
            color: #f2f2f2;
            text-align: center;
            padding: 10px 10px 0 16px;
            ">
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="{{_csrf.token}}" />
            <input type="submit" value="Sign Out"/>
        </form>
    </div>
</div>
<#nested>
</body>
</html>
</#macro>