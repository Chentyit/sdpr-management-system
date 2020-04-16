let briteChartApp = window.briteChartApp || {};
!function (e, t) {
    t.createDonutChart = function (e, t) {
        let a = britecharts.donut(), l = britecharts.legend();

        l.width(250).height(200)
            .colorSchema([
                "#0984e3",
                "#ff7675",
                "#fab1a0",
                "#a29bfe",
                "#6c5ce7",
                "#fd79a8",
                "#e84393"])
            .numberFormat("s");

        a.height(300).highlightSliceById(3)
            .colorSchema([
                "#0984e3",
                "#ff7675",
                "#fab1a0",
                "#a29bfe",
                "#6c5ce7",
                "#fd79a8",
                "#e84393"])
            .hasFixedHighlightedSlice(!0)
            .internalRadius(80)
            .on("customMouseOver", function (e) {
                l.highlight(e.data.id)
            })
            .on("customMouseOut", function () {
                l.clearHighlight()
            });

        d3.select(e)
            .datum(t)
            .call(a);
        d3.select(".legend-chart-container")
            .datum(t)
            .call(l)
    };

    e(function () {

        function getDofData() {
            let data_result = []

            $.ajax({
                "type": "post",
                "dataType": "json",
                "contentType": "application/json",
                "url": "/sta/getDofData",
                "async": false,
                "success": function (data) {
                    console.log(data);
                    for (let i = 0; i < data.length; i++) {
                        data_result.push({
                            id: data[i].themeId,
                            name: data[i].themeName,
                            quantity: data[i].num,
                            percentage: data[i].percentage
                        })
                    }
                    console.log(l);
                },
                "error": function (data) {
                }
            })

            return data_result;
        }

        function d() {
            e(".donut-container").length > 0 && briteChartApp.createDonutChart(".donut-container", getDofData())
        }

        e(window).on("resize", function (e) {
            e.preventDefault();
            setTimeout(d, 200)
        });

        d()
    })
}(jQuery, briteChartApp);