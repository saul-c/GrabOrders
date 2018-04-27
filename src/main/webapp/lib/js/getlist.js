$(document).ready(function() {
    var list;
    var page = "/graborders/list";
    $.ajax({
        type: "get",
        url: page,
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        success: function (result) {
            var s="";
            for(var i=0;i<result.length;i++){
                s+='<td>'+result[i].info+'</td>';
                s+='<td>'+result[i].number+'</td>';
                s+='<td>'+result[i].createTime+'</td>';
                s+='<td>'+result[i].startTime+'</td>';
                s+='<td>'+result[i].endTime+'</td>';
            }
            $('#table-info').innerHTML+=s;
            alert(result);
        }
    });
});