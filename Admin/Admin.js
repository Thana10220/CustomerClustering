$( document ).ready(function() {

    var table = $('#job_table').DataTable({
        orderCellsTop: true,
        fixedHeader: true
    });

    $.fn.loadData = function(){
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/api/getjob",
            cache: false,
            timeout: 600000,
            success: function (data) {
                console.log("SUCCESS : ", data);
                var row_num=0
                table.clear().draw();
                for(var count=0;count<data.length;count++){
                    row_num = row_num+1;
                    table.row.add( [
                        row_num,
                        data[count].job_ID,
                        (data[count].job_STATUS === 'finish')?'<b style="color: green">FINISH</b>':'<b style="color: gold">IN PROGRESS</b>',
                        data[count].job_DATE
                    ] ).draw();
                }

            },error: function (e) { console.log("ERROR : ", e); }});

    }

    $.fn.loadData();

    $("#processBtn").click(function (){
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/api/process",
            cache: false,
            timeout: 600000,
            success: function (data) {
                console.log("SUCCESS : ", data);
                $.fn.loadData();
            },error: function (e) { console.log("ERROR : ", e); }});
    });

    var config = $( "#dialog" ).dialog({
        autoOpen: false,
        width: 1000,
        height: 800,
        modal: true,
        open: function() {
            $(this).dialog("widget").find(".ui-dialog-titlebar").hide();
            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "/api/getinitpara",
                cache: false,
                timeout: 600000,
                success: function (data) {
                    console.log("SUCCESS : ", data);

                    $("#kmean").val(data.k_mean_cluster);
                    $("#dbscan").val(data.dbscan_eps);
                    $("#kmedoids").val(data.kmedoid_cluster);
                    $("#meanshift").val(data.meanshift_bndwidth);

                    $("#kmean_suggets").val(data.k_mean_cluster_suggest);
                    $("#dbscan_suggets").val(data.dbscan_eps_suggest);
                    $("#kmedoids_suggets").val(data.kmedoid_cluster_suggest);
                    $("#meanshift_suggets").val(data.meanshift_bndwidth_suggest);
                },error: function (e) { console.log("ERROR : ", e); }});
        },
        buttons: {
            "Update": function() {
                $(this).dialog("close");
                var kmean = $("#kmean").val();
                var dbscan = $("#dbscan").val();
                var kmedoids = $("#kmedoids").val();
                var meanshift = $("#meanshift").val();
                var data = {"KMEAN":kmean,"DB_SCAN":dbscan,"PAM":kmedoids,"MEANSHIFT":meanshift}
                $.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "/api/updatepara",
                    data : data,
                    cache: false,
                    timeout: 600000,
                    success: function (data) {
                        console.log("SUCCESS : ", data);
                        var message = (data === true)?'บันทึกเรียบร้อย':'พบข้อผิดพลาด';
                        alert(message)
                    },error: function (e) { console.log("ERROR : ", e); }});


            },
            Close: function() {
                $(this).dialog("close");
            }
        }
    });

    $("#configBtn").click(function (){
        config.dialog("open");
    });

});
