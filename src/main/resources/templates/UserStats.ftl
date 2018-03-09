<#import "spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Users</title>
    <link href="/css/style.css" rel="stylesheet">
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript" src="/scripts/charts.js"></script>
</head>

<body>
<div>
    <table>
        <tr>
            <th>Name</th>
            <th>Email</th>
        </tr>
    <#list users as user>
        <tr>
            <td>${user.name}</td>
            <td>${user.email}</td>
        </tr>
    </#list>
    </table>

</div>
</body>
</html>