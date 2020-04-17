$(".thesis-delete-custom").click(function () {
    // 获取到表格信息
    const l = $("tr").filter(".selected");

    // 获取 Id 集合
    let ids = []
    for (let i = 0; i < l.length; i++) {
        ids.push(parseInt(l[i].cells.item(0).innerText));
    }

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
})