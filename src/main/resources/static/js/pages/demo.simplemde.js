!function (e) {
    "use strict";
    var i = function () {
    };
    i.prototype.init = function () {
        new SimpleMDE({
            element: document.getElementById("simplemde1"),
            spellChecker: !1,
            autosave: {enabled: !0, unique_id: "simplemde1"}
        })
    }, e.SimpleMDEEditor = new i, e.SimpleMDEEditor.Constructor = i
}(window.jQuery), function (e) {
    "use strict";
    e.SimpleMDEEditor.init()
}(window.jQuery);