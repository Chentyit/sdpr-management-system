var options = {
    chart: {height: 350, type: "radar"},
    series: [{name: "Series 1", data: [80, 50, 30, 40, 100, 20]}],
    colors: ["#727cf5"],
    labels: ["January", "February", "March", "April", "May", "June"]
};
(chart = new ApexCharts(document.querySelector("#basic-radar"), options)).render();
options = {
    chart: {height: 350, type: "radar"},
    series: [{name: "Series 1", data: [20, 100, 40, 30, 50, 80, 33]}],
    labels: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
    plotOptions: {radar: {size: 140}},
    colors: ["#FF4560"],
    markers: {size: 4, colors: ["#fff"], strokeColor: "#FF4560", strokeWidth: 2},
    tooltip: {
        y: {
            formatter: function (e) {
                return e
            }
        }
    },
    yaxis: {
        tickAmount: 7, labels: {
            formatter: function (e, r) {
                return r % 2 == 0 ? e : ""
            }
        }
    }
};
(chart = new ApexCharts(document.querySelector("#radar-polygon"), options)).render();
var chart;
options = {
    chart: {height: 350, type: "radar"},
    series: [{name: "Series 1", data: [80, 50, 30, 40, 100, 20]}, {
        name: "Series 2",
        data: [20, 30, 40, 80, 20, 80]
    }, {name: "Series 3", data: [44, 76, 78, 13, 43, 10]}],
    stroke: {width: 0},
    fill: {opacity: .4},
    markers: {size: 0},
    legend: {offsetY: -10},
    colors: ["#727cf5", "#02a8b5", "#fd7e14"],
    labels: ["2011", "2012", "2013", "2014", "2015", "2016"]
};

function update() {
    function e() {
        for (var e = [], r = 0; r < 6; r++) e.push(Math.floor(100 * Math.random()));
        return e
    }

    chart.updateSeries([{name: "Series 1", data: e()}, {name: "Series 2", data: e()}, {name: "Series 3", data: e()}])
}

(chart = new ApexCharts(document.querySelector("#radar-multiple-series"), options)).render();