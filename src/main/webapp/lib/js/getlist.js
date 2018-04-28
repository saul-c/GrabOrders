$(document).ready(function() {
    var page = "/graborders/list";
    $.ajax({
        type: "get",
        url: page,
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        success: function (result) {
            var s=new StringBuffer();
            for(var i=0;i<result.length;i++){
                s.append('<tr>');
                s.append('<td>'+result[i].info+'</td>');
                s.append('<td>'+result[i].number+'</td>');
                s.append('<td>'+result[i].startTime+'</td>');
                s.append('<td>'+result[i].endTime+'</td>');
                s.append('<td><a class="btn btn-info" href="graborders/') +
                s.append(result[i].orderId+'/detail" target="_blank">抢购</a></td>')
                s.append('</tr>');
            }
            $('#table-info').html(s.toString());
        }
    });
});
function StringBuffer() {
    this.__strings__ = new Array;
}

StringBuffer.prototype.append = function (str) {
    this.__strings__.push(str);
};

StringBuffer.prototype.toString = function () {
    return this.__strings__.join("");
};