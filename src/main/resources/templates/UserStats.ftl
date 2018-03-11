<#import "spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Users</title>
    <link href="/css/style.css" rel="stylesheet">
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript" src="/scripts/charts.js"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
</head>

<body>
<script type="text/javascript">
    google.load("visualization", "1", {packages: ['controls','corechart','table']});
    function prepareData() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'User name');
        data.addColumn('string', 'Email}');
        data.addRows([
        <#list users as user>
            ['${user.name}', '${user.email}'],
        </#list>]);
        drawTableChart(data, 'Contributors list', 'chart_div');
    }
    google.charts.setOnLoadCallback(prepareData);
</script>
<div class="row">
    <div class="col-1", id="chart_div"></div>
</div>

</body>
</html>