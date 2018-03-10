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
    google.load("visualization", "1", {packages: ["corechart"]});

    function doStats() {

        var data = new google.visualization.DataTable();
        data.addColumn('string', '${column1}');
        data.addColumn('number', '${column2}');
        data.addColumn({type: 'number', role: 'annotation'});

        data.addRows([
        <#list commitsModels as commitModel>
            ['${commitModel.email}', ${commitModel.count?c} , ${commitModel.count?c}],
        </#list>]);
        drawChart(data, 'Total number of commits', 'chart_div');
    }

    function doAvg() {
        var data2 = new google.visualization.DataTable();
        data2.addColumn('string', '${avgCol1}');
        data2.addColumn('number', '${avgCol2}');
        data2.addColumn({type: 'number', role: 'annotation'});

        data2.addRows([
        <#list averageCommitsModels as averageCommitsModel>
            ['${averageCommitsModel.email}', ${averageCommitsModel.averageCommitsPerMonth?c} , ${averageCommitsModel.averageCommitsPerMonth?c}],
        </#list>]);
        drawChart(data2, 'Average number of commits per month', 'avg_chart_div');
    }

    google.charts.setOnLoadCallback(doStats);
    google.charts.setOnLoadCallback(doAvg);

</script>
<div class="row">
    <div class="col-1", id="chart_div"></div>
    <div class="col-1", id="avg_chart_div"></div>
</div><!-- /.row -->

</body>
</html>