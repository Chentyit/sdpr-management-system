new Dropzone(".dropzone", {
    url: "/thesis/upload",
    method: "post",
    paramName: "multipartFile",
    maxFiles: 1,            //一次性上传的文件数量上限
    maxFilesize: 2,         //文件大小，单位：MB
    acceptedFiles: ".json",
    addRemoveLinks: true,
    parallelUploads: 1,     //一次上传的文件数量
    dictDefaultMessage: '拖动文件至此或者点击上传',
    dictMaxFilesExceeded: "您最多只能上传1个文件！",
    dictResponseError: '文件上传失败!',
    dictInvalidFileType: "文件类型只能是*.json",
    dictFallbackMessage: "浏览器不受支持",
    dictFileTooBig: "文件过大上传文件最大支持.",
    dictRemoveLinks: "删除",
    dictCancelUpload: "取消",
    init: function () {
        this.on("addedfile", function (file) {
            //上传文件时触发的事件

        });
        this.on("success", function (file, data) {
            //上传成功触发的事件
            console.log("success")
        });
        this.on("error", function (file, data) {
            //上传失败触发的事件
            console.log("error")
        });
        this.on("removedfile", function (file) {//删除文件触发结果
            //console.log(file);
            console.log("removedfile")
        });
    }
});