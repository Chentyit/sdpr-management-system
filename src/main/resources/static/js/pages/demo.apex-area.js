

function getNopData() {

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

    $.ajax({
        "type": "post",
        "dataType": "json",
        "contentType" : "application/json",
        "url": "/sta/getNopData",
        "async": false,
        "success": function (data) {
            for (let i = 0; i < data.length; i++) {
                options['series'].push({
                    name: data[i].themeName,
                    data: data[i].nums
                })
            }

            let date = new Date;
            let year = date.getFullYear();
            for (let i = year - 9; i <= year; i++) {
                options['xaxis']['categories'].push(i);
            }
        },
        "error": function (data) {
            console.log(data)
        }
    })

    return options
}

$(function () {
    options = getNopData();
    (chart = new ApexCharts(document.querySelector("#spline-area"), options)).render();
})