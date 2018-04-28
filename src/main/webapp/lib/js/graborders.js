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
                        s.append('<td><a class="btn btn-info" href="/graborders/') +
                        s.append(list[i].orderId+'/detail" target="_blank">抢购</a></td>')
                        s.append('</tr>');
                    }
                    $('#table-info').html(s.toString());
            },"json")},
    },
    orderdetail:{
        init:function (order) {
            /*check login
            * TODO*/
            var startTime=order['startTime'];
            var endTime=order['endTime'];
            $.get(graborders.URL.gettime(),{},function (result) {
                var nowTime=result["data"];
                if(nowTime>endTime){

                }else if(nowTime<startTime){
                    var count=parseInt((startTime-nowTime)/1000);
                    $(function () {
                        graborders.orderdetail.countdown(count);
                    })
                }else{

                }
            },"json")
        },
        countdown:function timer(intDiff){
            window.setInterval(function(){
                var day=0,
                    hour=0,
                    minute=0,
                    second=0;//时间默认值
                if(intDiff > 0){
                    day = Math.floor(intDiff / (60 * 60 * 24));
                    hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
                    minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
                    second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
                }
                if (minute <= 9) minute = '0' + minute;
                if (second <= 9) second = '0' + second;
                $('#day_show').html(day+"天");
                $('#hour_show').html('<s id="h"></s>'+hour+'时');
                $('#minute_show').html('<s></s>'+minute+'分');
                $('#second_show').html('<s></s>'+second+'秒');
                intDiff--;
            }, 1000);
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