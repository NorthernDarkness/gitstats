<#import "spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Dark Admin</title>

    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/local.css"/>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript" src="/scripts/charts.js"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
          integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
</head>

<body>
<header class="app-header navbar navbar-inverse bg-inverse">

    <button class="navbar-toggler mobile-sidebar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent" aria-expanded="false"
            aria-label="Toggle navigation">☰
    </button>
    <a class="navbar-brand" href="#">Git statistic</a>

</header>
<script type="text/javascript">
    google.load("visualization", "1", {packages: ['controls', 'corechart', 'table']});

    function prepareData() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Attribute');
        data.addColumn('string', 'Value');
        data.addRows([
            ['Total number of commits', '${model.numberOfCommits?c}'],
            ['Total number of files', '${model.numberOfFiles?c}'],
            ['Average number of commits per month', '${model.averageCommitNumberPerMonth?c}'],
            ['Total number of users', '${model.numberOfUsers?c}'],
            ['The most frequently changed file', '${model.mostChangableFile}'],
            ['Total number of added lines', '${model.numberOfAddedLines?c}'],
            ['Total number of removed lines', '${model.numberOfRemovedLines?c}'],
        ]);
        drawTableChart(data, 'Contributors list', 'chart_div');
    }

    google.charts.setOnLoadCallback(prepareData);
</script>
<div class="app-body ">

    <div class="sidebar ">
        <nav class="sidebar-nav collapse navbar-collapse" id="navbarToggleExternalContent">
            <ul class="nav">
                <li class="nav-item nav-dropdown open"><a class="nav-link nav-dropdown-toggle" href="#"><i
                        class="icon-puzzle"></i>Dashboards</a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item"><a class="nav-link" href="/commits/stat"><i
                                class="icon-puzzle"></i>Project</a></li>
                        <li class="nav-item"><a class="nav-link" href="/commits/all"><i
                                class="icon-puzzle"></i>Commits</a></li>
                        <li class="nav-item"><a class="nav-link" href="/users/getAll"><i
                                class="icon-puzzle"></i>Users </a></li>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>

    <div class="main">
        <div class="container-fluid">
            <div class="row"><h5>Statistic for project ${model.name}:</h5></div>
            <div class="row" id="chart_div"></div>
        </div>
    </div>
</div>

<footer class="app-footer ">
    <span class="float-left">Git statistic © 2017.</span>
    <span class="float-right">Created by <a
            href="mailto:septentrio2013@yandex.ru">Alexandr Martynichev</a>
		</span>
</footer>
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
        integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
        integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
        integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
        crossorigin="anonymous"></script>
</body>
</html>