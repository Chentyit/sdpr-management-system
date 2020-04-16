series_1 = [31, 40, 28, 51, 42, 109, 100, 40, 28, 51]
series_2 = [11, 32, 45, 32, 34, 52, 666, 32, 34, 52]

series = [
    {
        name: "A",
        data: series_1
    },
    {
        name: "B",
        data: series_2
    }
]

years = ["2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"]

$(function () {

    options = {
        chart: {
            height: 380,
            type: "area"
        },
        dataLabels: {
            enabled: !1
        },
        stroke: {
            width: 3,
            curve: "smooth"
        },
        colors: ["#727cf5", "#6c757d"],
        series: series,
        legend: {
            offsetY: -10
        },
        xaxis: {
            categories: years
        },
        tooltip: {
            fixed: {
                enabled: !1,
                position: "topRight"
            }
        },
        grid: {
            row: {
                colors: ["transparent", "transparent"], opacity: .2
            },
            borderColor: "#f1f3fa"
        }
    };

    options['series'] = series;
    options['xaxis']['categories'] = years;

    (chart = new ApexCharts(document.querySelector("#spline-area"), options)).render();
})