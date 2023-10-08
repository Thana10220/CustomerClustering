$( document ).ready(function() {

    $('#customertable thead tr')
        .clone(true)
        .addClass('filters')
        .appendTo('#customertable thead');

    pdfMake.fonts = {
        THSarabun: {
            normal: 'THSarabun.ttf',
            bold: 'THSarabun-Bold.ttf',
            italics: 'THSarabun-Italic.ttf',
            bolditalics: 'THSarabun-BoldItalic.ttf'
        }
    }

    var table = $('#customertable').DataTable({
        orderCellsTop: true,
        fixedHeader: true,
        "dom": 'Bfrtip',
        "buttons": [
            {
                "extend": 'pdf',
                "text": 'PDF',
                "pageSize": 'A4',
                "customize":function(doc){
// กำหนด style หลัก
                    doc.defaultStyle = {
                        font:'THSarabun',
                        fontSize:16
                    };

                    console.log(doc);
                }
            },
        ],
        initComplete: function () {
            var api = this.api();

// For each column
            api
                .columns()
                .eq(0)
                .each(function (colIdx) {
// Set the header cell to contain the input element
                    var cell = $('.filters th').eq(
                        $(api.column(colIdx).header()).index()
                    );
                    var title = $(cell).text();
                    $(cell).html('<input type="text" placeholder="' + title + '" />');

// On every keypress in this input
                    $(
                        'input',
                        $('.filters th').eq($(api.column(colIdx).header()).index())
                    )
                        .off('keyup change')
                        .on('change', function (e) {
// Get the search value
                            $(this).attr('title', $(this).val());
                            var regexr = '({search})'; //$(this).parents('th').find('select').val();

                            var cursorPosition = this.selectionStart;
// Search the column for that value
                            api
                                .column(colIdx)
                                .search(
                                    this.value != ''
                                        ? regexr.replace('{search}', '(((' + this.value + ')))')
                                        : '',
                                    this.value != '',
                                    this.value == ''
                                )
                                .draw();
                        })
                        .on('keyup', function (e) {
                            e.stopPropagation();

                            $(this).trigger('change');
                            $(this)
                                .focus()[0]
                                .setSelectionRange(cursorPosition, cursorPosition);
                        });
                });
        },
    });

    const ctx = document.getElementById('myChart');

    const label = [];
    const item = [];
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/api/getDashboard",
        cache: false,
        timeout: 600000,
        success: function (data) {
             console.log("SUCCESS : ", data);
             for(var count=0;count<data.length;count++){
                 label.push(data[count].promotion_RESULT);
                 item.push(data[count].counter);

             }
        },error: function (e) { console.log("ERROR : ", e); }});

   const data = {
       labels: label,
       datasets: [{
           label: '# Promotion',
           data: item,
           borderWidth: 1
       }]
   }

    new Chart(ctx, {
        type: 'bar',
        data: data,
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    $("#myChart").css("height",500);
    $("#myChart").css("width",1500);

    $.fn.loadData = function(){

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/api/getReport",
            cache: false,
            timeout: 600000,
            success: function (data) {
                console.log("SUCCESS : ", data);
                var row_num=0
                table.clear().draw();
                for(var count=0;count<data.length;count++){
                    row_num = row_num+1;
                    var ciphertext = CryptoJS.AES.encrypt(data[count].customer_NAME, 'G0nnC85qIQ');
                    table.row.add( [
                        row_num,
                        ciphertext,
                        data[count].customer_ADDR,
                        data[count].customer_TEL,
                        data[count].promotion_RESULT
                    ] ).draw();
                }



            },error: function (e) { console.log("ERROR : ", e); }});

    }

    $.fn.loadData();
    openCity(event, 'Customer');

});
function openCity(evt, cityName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
}