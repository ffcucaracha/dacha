$(document).on('click', '#btn-bugaga', function() {
    $('.bazinga').show();
});

$(document).on("click", "[id^='open-dialog-']", function(){
    var id_st = $(this).attr("id");
    var id_ar = id_st.split('-');
    var id = "#dialog-"+id_ar[id_ar.length-1];
    //alert(id);
    $(id).dialog({
        autoOpen: false,
    });
    $(id).dialog("open");
});