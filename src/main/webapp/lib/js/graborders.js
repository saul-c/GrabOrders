/*js函数封装*/
var graborders={
    URL:{
        getlist:function () {
          return "/graborders/list";
        },
        gettime:function () {
           return "/graborders/time/now";
        }
    },
    ajax:{
        getlist:function(){
            $.get(graborders.URL.getlist(),{},function (list) {
                    var s=new StringBuffer();
                    for(var i=0;i<list.length;i++){
                        s.append('<tr>');
                        s.append('<td>'+list[i].info+'</td>');
                        s.append('<td>'+list[i].number+'</td>');
                        s.append('<td>'+list[i].startTime+'</td>');
                        s.append('<td>'+list[i].endTime+'</td>');
                        s.append('<td><a class="btn btn-info" href="graborders/') +
                        s.append(list[i].orderId+'/detail" target="_blank">抢购</a></td>')
                        s.append('</tr>');
                    }
                    $('#table-info').html(s.toString());
            },"json")},

        gettime:function(){
            $.get(graborders.URL.gettime(),{},function (result) {

            },"json")
        }
    }

}
/*util*/
function StringBuffer() {
    this.__strings__ = new Array;
}

StringBuffer.prototype.append = function (str) {
    this.__strings__.push(str);
};

StringBuffer.prototype.toString = function () {
    return this.__strings__.join("");
};