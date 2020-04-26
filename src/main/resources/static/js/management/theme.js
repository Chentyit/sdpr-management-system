$(function () {
    // 单项删除
    $(document).on("click", ".theme-delete-btn-custom", function () {
        let themeId = $(this).attr("id")

        console.log(themeId)
        deleteTheme(parseInt(themeId))
    })

    function deleteTheme(themeId) {
        $.ajax({
            "type": "get",
            "dataType": "json",
            "contentType": "application/json",
            "async": false,
            "url": "/theme/delete/" + themeId,
            "success": function (data) {
                if(data['flag']) {
                    window.location.href = "/theme"
                } else {
                    console.log("后台数据库删除数据失败")
                }
            },
            "error": function (data) {
                console.log("前端发送任务失败")
            }
        })
    }
})