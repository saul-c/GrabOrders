/*js函数封装*/
var graborders={
    URL:{
        getlist:function () {
            return "/graborders/list";
        },
        gettime:function () {
            return "/graborders/time/now";
        },
        getexposer:function (orderId) {
            return "/graborders/"+orderId+"/exposer";
        },
        getlogin:function () {
            return "/graborders/login";
        },
        gettokenlogin:function (token) {
            return "/graborders/"+token+"/login";
        },
        getregister:function () {
            return "/graborders/register";
        },
        execution:function (orderId,md5) {
            return "/graborders/"+orderId+"/"+md5+"/execution";
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
            var startTime=order['startTime'];
            var endTime=order['endTime'];
            var orderId=order['orderId'];
            $.get(graborders.URL.gettime(),{},function (result) {
                var nowTime=result["data"];
                if(nowTime>endTime){
                    $('#message').html('<h2>对不起,您来晚了,秒杀已结束!</h2>');
                }else if(nowTime<startTime){
                    var count=parseInt((startTime-nowTime)/1000);
                    $(function () {
                        graborders.orderdetail.countdown(count,orderId);
                    })
                }else{
                    $(function () {
                        graborders.orderdetail.getexposer(orderId);
                    })
                }
            },"json")
        },
        countdown:function timer(intDiff,orderId){
            var interval=window.setInterval(function(){
                var day=0,
                    hour=0,
                    minute=0,
                    second=0;//时间默认值
                if(intDiff > 0){
                    day = Math.floor(intDiff / (60 * 60 * 24));
                    hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
                    minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
                    second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
                }else{
                    $(function () {
                        clearInterval(interval);
                        graborders.orderdetail.getexposer(orderId);
                    })
                }
                if (minute <= 9) minute = '0' + minute;
                if (second <= 9) second = '0' + second;
                $('#day_show').html(day+"天");
                $('#hour_show').html('<s id="h"></s>'+hour+'时');
                $('#minute_show').html('<s></s>'+minute+'分');
                $('#second_show').html('<s></s>'+second+'秒');
                intDiff--;
            }, 1000);
        },
        getexposer:function (orderId) {
            $.post(graborders.URL.getexposer(orderId),{},function (result) {
                if(result['success']){
                    var exposer=result['data'];
                    if(exposer['exposed']){
                        var md5=exposer['md5'];
                        var orderId=exposer['orderId'];
                        $('#btn').attr('disabled',false);
                        $('#btn').one('click',function () {
                            $('#btn').attr('disabled',true);
                            $(function () {
                                graborders.orderdetail.execution(orderId,md5);
                            })
                        })
                    }else{
                        var nowTime=exposer['nowTime'];
                        var startTime=exposer['startTime'];
                        var endTime=exposer['endTime'];
                        $(function () {
                            var count=parseInt((startTime-nowTime)/1000);
                            graborders.orderdetail.countdown(count,orderId);
                        })
                    }
                }
            })
        },
        execution:function (orderId,md5) {
            /*TODO
            加入token验证
             */
            $.post(graborders.URL.execution(orderId,md5),{},function (result) {
                if(result['success']){
                    alert("秒杀成功！");
                }else{
                    alert("秒杀失败！");
                }
            },"json")
        }
    },
    login:{
        useTokenlogin:function (token) {
            $.post(graborders.URL.gettokenlogin(token),{},function (result) {
                if(result['success']){
                    var logininfo=result['data'];
                    var user=logininfo['user'];
                    $("#login-control").html("<h4>欢迎您:"+user['phoneNumber']+"</h4>");
                }else{
                    delCookie("token");
                }
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
function setCookie(name,value) {
    var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ encodeURI(value) + ";expires=" + exp.toGMTString();
}
function getCookie(name) {
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return decodeURI(arr[2]);
    else
        return null;
}
function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}

/*登陆界面所需函数*/
var checkregister=[false,false,false,false];
var checklogin=[false,false];

//校验成功函数
function success(Obj,counter,check,offset) {
    Obj.parent().parent().removeClass('has-error').addClass('has-success');
    $('.tips').eq(counter+offset).hide();
    $('.glyphicon-ok').eq(counter+offset).show();
    $('.glyphicon-remove').eq(counter+offset).hide();
    check[counter] = true;

}

// 校验失败函数
function fail(Obj,counter,msg,check,offset) {
    Obj.parent().parent().removeClass('has-success').addClass('has-error');
    $('.glyphicon-remove').eq(counter+offset).show();
    $('.glyphicon-ok').eq(counter+offset).hide();
    $('.tips').eq(counter+offset).text(msg).show();
    check[counter] = false;
}

/*注册逻辑*/

//手机号码
var regPhoneNum = /^[0-9]{11}$/
$('#register').find('input').eq(0).change(function() {
    if (regPhoneNum.test($(this).val())) {
        success($(this), 0,checkregister,0);
    } else {
        fail($(this), 0, '手机号码只能为11位数字',checkregister,0);
    }
});

// 密码匹配
var regPasswordSpecial = /[~!@#%&=;':",./<>_\}\]\-\$\(\)\*\+\.\[\?\\\^\{\|]/;
var regPasswordAlpha = /[a-zA-Z]/;
var regPasswordNum = /[0-9]/;
// 匹配字母、数字、特殊字符至少两种的函数
function atLeastTwo(password) {
    var a = regPasswordSpecial.test(password) ? 1 : 0;
    var b = regPasswordAlpha.test(password) ? 1 : 0;
    var c = regPasswordNum.test(password) ? 1 : 0;
    return a + b + c;

}

$('#register').find('input').eq(1).change(function() {
    password = $(this).val();
    if ($(this).val().length < 6) {
        fail($(this), 1, '密码太短，不能少于6个字符',checkregister,0);
    } else {
        if (atLeastTwo($(this).val()) < 2) {
            fail($(this), 1, '密码中至少包含字母、数字、特殊字符的两种',checkregister,0)
        } else {
            success($(this),1,checkregister,0);
        }
    }
});

// 再次输入密码校验
$('#register').find('input').eq(2).change(function() {
    if ($(this).val() == password) {
        success($(this),2,checkregister,0);
    } else {
        fail($(this), 2, '两次输入的密码不一致', checkregister,0);
    }
});

$('#register').find('input').eq(3).change(function() {
    var IsBy = $.idcode.validateCode();
    if (IsBy) {
        success($(this),3,checkregister,0);
    } else {
        fail($(this), 3, '验证码输入错误',checkregister,0);
    }
});

$('#btn-register').click(function(e) {
    if (!checkregister.every(function(value) {
            return value == true
        })) {
        e.preventDefault();
        for (key in checkregister) {
            if (!checkregister[key]) {
                $('#register').find('input').eq(key).parent().parent().removeClass('has-success').addClass('has-error')
            }
        }
    }else{
        var phonenumber=document.getElementById("re-phoneNum").value;
        var passwd=document.getElementById("re-password").value;
        var user={'phoneNumber':phonenumber,'passWd':passwd};
        var JSONdata=JSON.stringify(user);
        $.ajax({
            type:"post",
            url: graborders.URL.getregister(),
            data:JSONdata,
            dataType:"json",
            contentType : "application/json;charset=UTF-8",
            success: function(result){
                if(result['success']){
                    alert("注册成功");
                    var logininfo=result['data'];
                    var user=logininfo['user'];
                    var token=logininfo['token'];
                    $("#login-control").html("<h4>欢迎您:"+user['phoneNumber']+"</h4>");
                    $("#register").modal('hide');
                    setCookie("token",token['token']);
                }else{
                    alert("注册失败");
                }
            }
        });
    }
});

/*登陆逻辑*/

//手机号码
$('#login').find('input').eq(0).change(function() {
    if (regPhoneNum.test($(this).val())) {
        success($(this),0,checklogin,4);
    } else {
        fail($(this), 0, '手机号码只能为11位数字',checklogin,4);
    }
});

$('#login').find('input').eq(1).change(function() {
    password = $(this).val();
    if ($(this).val().length < 6) {
        fail($(this), 1, '密码太短，不能少于6个字符',checklogin,4);
    } else {
        if (atLeastTwo($(this).val()) < 2) {
            fail($(this), 1, '密码中至少包含字母、数字、特殊字符的两种',checklogin,4)
        } else {
            success($(this),1,checklogin,4);
        }
    }
});

$('#btn-login').click(function(e) {
    if (!checklogin.every(function(value) {
            return value == true
        })) {
        e.preventDefault();
        for (key in checklogin) {
            if (!checklogin[key]) {
                $('#login').find('input').eq(key).parent().parent().removeClass('has-success').addClass('has-error')
            }
        }
    }else{
        var phonenumber=document.getElementById("lg-phoneNum").value;
        var passwd=document.getElementById("lg-password").value;
        var user={'phoneNumber':phonenumber,'passWd':passwd};
        var JSONdata=JSON.stringify(user);
        $.ajax({
            type:"post",
            url: graborders.URL.getlogin(),
            data:JSONdata,
            dataType:"json",
            contentType : "application/json;charset=UTF-8",
            success: function(result){
                if(result['success']){
                    var logininfo=result['data'];
                    var user=logininfo['user'];
                    var token=logininfo['token'];
                    $("#login-control").html("<h4>欢迎您:"+user['phoneNumber']+"</h4>");
                    $("#login").modal('hide');
                    setCookie("token",token['token']);
                }else{
                    alert("登陆失败");
                }
            }
        });
    }
});
