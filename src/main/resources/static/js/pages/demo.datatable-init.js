$(document).ready(
    function () {
        "use strict";
        $("#selection-datatable").DataTable(
            {
                select: { style: "multi" },
                language: { paginate: { previous: "<i class='mdi mdi-chevron-left'>", next: "<i class='mdi mdi-chevron-right'>" } },
                drawCallback: function () {
                    $(".dataTables_paginate > .pagination").addClass("pagination-rounded")
                }
            }
        ), 
        a.buttons().container().appendTo("#datatable-buttons_wrapper .col-md-6:eq(0)")
    }
);