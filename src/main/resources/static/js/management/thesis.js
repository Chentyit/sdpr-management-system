$(function () {
    // 点击条目 delete 删除
    $(document).on("click", ".thesis-delete-btn-custom", function () {
        let thesisId = $(this).attr("id")

        // 将 Id 值传递给弹窗
        let tar = $(".modal-body p")
        tar.text("||" + thesisId + "|| 数据条目即将删除，是否确认操作!!!")
    })

    $(document).on("click", ".thesis-delete-one-custom", function () {
        let item = $(".modal-body p").text()

        let ids = [item.split("||")[1].split("-")[1]]

        deleteByIds(ids)
    })

    // 多项选中删除
    $(document).on("click", ".thesis-delete-selected-custom", function () {
        // 获取到表格信息
        const l = $("tr").filter(".selected");

        // 获取 Id 集合
        let ids = []
        for (let i = 0; i < l.length; i++) {
            ids.push(l[i].cells.item(0).innerText);
        }
        deleteByIds(ids)
    })

    function deleteByIds(ids) {
        // 将要删除的信息 Id 发送给后端
        $.ajax({
            "type": "post",
            "dataType": "text",
            "contentType": "application/json",
            "async": false,
            "url": "/thesis/delete",
            "data": JSON.stringify(ids),
            "success": function (data) {
                console.log("success")
                window.location.href = "/thesis"
            },
            "error": function (data) {
                console.log("failure")
            }
        })
    }
})