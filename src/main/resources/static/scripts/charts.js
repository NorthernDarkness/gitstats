// Load the Visualization API and the corechart package.
google.charts.load('current', {'packages': ['corechart']});

// Callback that creates and populates a data table,
// instantiates the pie chart, passes in the data and
// draws it.
function drawChart(data, title, divName) {

    // Set chart options
    var options = {
        'title': title,
        // 'width': '100%',
        'height': data.getNumberOfRows() * 50
    };

    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.BarChart(document.getElementById(divName));
    chart.draw(data, options);
}