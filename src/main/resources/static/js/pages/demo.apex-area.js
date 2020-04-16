function getNopData() {
    let options_nop = {
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
        colors: [
            "#74b9ff",
            "#0984e3",
            "#ff7675",
            "#d63031",
            "#a29bfe",
            "#6c5ce7",
            "#fd79a8",
            "#e84393",
            "#00cec9",
            "#fab1a0",
            "#e17055"
        ],
        series: [],
        legend: {
            offsetY: -10
        },
        xaxis: {
            categories: []
        },
        tooltip: {
            fixed: {
                enabled: !1,
                position: "topRight"
            }
        },
        grid: {
            row: {
                colors: ["transparent", "transparent"],
                opacity: .2
            },
            borderColor: "#f1f3fa"
        }
    };

    let options_rpof = {
        chart: {
            height: 380,
            type: "bar",
            stacked: !0,
            stackType: "100%",
            toolbar: {
                show: !1
            }
        },
        plotOptions: {
            bar: {
                horizontal: !0
            }
        },
        stroke: {
            width: 1,
            colors: ["#fff"]
        },
        series: [],
        xaxis: {
            categories: []
        },
        colors: ["#ffbc00", "#39afd1", "#6c757d", "#e3eaef", "#727cf5"],
        tooltip: {
            y: {
                formatter: function (e) {
                    return e + "K"
                }
            }
        },
        fill: {opacity: 1},
        states: {hover: {filter: "none"}},
        legend: {position: "top", horizontalAlign: "center"},
        grid: {borderColor: "#f1f3fa"}
    };

    $.ajax({
        "type": "post",
        "dataType": "json",
        "contentType" : "application/json",
        "url": "/sta/getNopData",
        "async": false,
        "success": function (data) {
            for (let i = 0; i < data.length; i++) {
                options_nop['series'].push({
                    name: data[i].themeName,
                    data: data[i].nums
                })

                options_rpof['series'].push({
                    name: data[i].themeName,
                    data: data[i].nums
                })
            }

            let date = new Date;
            let year = date.getFullYear();
            for (let i = year - 9; i <= year; i++) {
                options_nop['xaxis']['categories'].push(i);

                options_rpof['xaxis']['categories'].push(i);
            }
        },
        "error": function (data) {
            console.log(data)
        }
    })

    return [options_nop, options_rpof]
}

$(function () {
    options_list = getNopData();
    (chart = new ApexCharts(document.querySelector("#spline-area"), options_list[0])).render();
    (chart = new ApexCharts(document.querySelector("#full-stacked-bar"), options_list[1])).render();
})