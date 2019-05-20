<#macro page>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <title>FitDB</title>
    <meta charset=UTF-8" name="viewport" content="width=device-width, initial-scale=1"/>
    <style>
        body{
            margin: 0 0 1px;
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
    <a href="/clients"> Clients list</a>
    <a href="/instructors"> Instructors list</a>
    <a href="/disciplines"> Disciplines list</a>
    <a href="/instructs"> Instruct </a>
    <a href="/schedules"> Schedules </a>
    <a href="/subscriptions">Subscriptions</a>
    <a href="/reports"> Report </a>
</div>
<#nested>
</body>
</html>
</#macro>