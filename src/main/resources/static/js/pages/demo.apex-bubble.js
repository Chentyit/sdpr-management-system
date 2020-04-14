function generateData(e, a, t) {
    for (var r = 0, n = []; r < a;) {
        var o = Math.floor(750 * Math.random()) + 1, m = Math.floor(Math.random() * (t.max - t.min + 1)) + t.min,
            i = Math.floor(61 * Math.random()) + 15;
        n.push([o, m, i]), 864e5, r++
    }
    return n
}

var options = {
    chart: {height: 380, type: "bubble", toolbar: {show: !1}},
    dataLabels: {enabled: !1},
    series: [{
        name: "Bubble 1",
        data: generateData(new Date("11 Feb 2017 GMT").getTime(), 20, {min: 10, max: 60})
    }, {
        name: "Bubble 2",
        data: generateData(new Date("11 Feb 2017 GMT").getTime(), 20, {min: 10, max: 60})
    }, {name: "Bubble 3", data: generateData(new Date("11 Feb 2017 GMT").getTime(), 20, {min: 10, max: 60})}],
    fill: {opacity: .8, gradient: {enabled: !1}},
    colors: ["#727cf5", "#ffbc00", "#fa5c7c"],
    xaxis: {tickAmount: 12, type: "category"},
    yaxis: {max: 70},
    grid: {borderColor: "#f1f3fa"},
    legend: {offsetY: -10}
};

function generateData1(e, a, t) {
    for (var r = 0, n = []; r < a;) {
        var o = Math.floor(Math.random() * (t.max - t.min + 1)) + t.min, m = Math.floor(61 * Math.random()) + 15;
        n.push([e, o, m]), e += 864e5, r++
    }
    return n
}

(chart = new ApexCharts(document.querySelector("#simple-bubble"), options)).render();
var chart, options2 = {
    chart: {height: 380, type: "bubble", toolbar: {show: !1}},
    dataLabels: {enabled: !1},
    series: [{
        name: "Product 1",
        data: generateData1(new Date("11 Feb 2017 GMT").getTime(), 20, {min: 10, max: 60})
    }, {
        name: "Product 2",
        data: generateData1(new Date("11 Feb 2017 GMT").getTime(), 20, {min: 10, max: 60})
    }, {
        name: "Product 3",
        data: generateData1(new Date("11 Feb 2017 GMT").getTime(), 20, {min: 10, max: 60})
    }, {name: "Product 4", data: generateData1(new Date("11 Feb 2017 GMT").getTime(), 20, {min: 10, max: 60})}],
    fill: {type: "gradient"},
    colors: ["#727cf5", "#0acf97", "#fa5c7c", "#39afd1"],
    xaxis: {tickAmount: 12, type: "datetime", labels: {rotate: 0}},
    yaxis: {max: 70},
    legend: {offsetY: -10},
    grid: {borderColor: "#f1f3fa"}
};
(chart = new ApexCharts(document.querySelector("#second-bubble"), options2)).render();