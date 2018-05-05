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
        gettokenlogin:function (token) {
            return "/graborders/"+token+"/login";
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
            /*check login
            * TODO*/
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
            $.get(graborders.URL.gettokenlogin(token),{},function (result) {
                if(result['success']){
                    var logininfo=result['data'];
                    var user=logininfo['user'];
                    $("#login-control").html("<h4>欢迎您:"+user['userName']+"</h4>");
                }else{
                    delCookie("token");
                }
            },"json")
        },
        register:function () {

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

//设置验证码
$(document).ready(function () {
    $.idcode.setCode();
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
        alert("ajax");
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
        alert("loginajax");
    }
});

/*验证码util*/
var settings = {
    e: 'idcode',
    codeType: {
        name: 'follow',
        len: 4
    }, //len是修改验证码长度的
    codeTip: '换个验证码?',
    inputID: 'idcode-btn' //验证元素的ID
};

var _set = {
    storeLable: 'codeval',
    store: '#ehong-code-input',
    codeval: '#ehong-code'
}
$.idcode = {
    getCode: function(option) {
        _commSetting(option);
        return _storeData(_set.storeLable, null);
    },
    setCode: function(option) {
        _commSetting(option);
        _setCodeStyle("#" + settings.e, settings.codeType.name, settings.codeType.len);

    },
    validateCode: function(option) {
        _commSetting(option);
        var inputV;
        if (settings.inputID) {
            inputV = $('#' + settings.inputID).val();

        } else {
            inputV = $(_set.store).val();
        }
        if (inputV.toUpperCase() == _storeData(_set.storeLable, null).toUpperCase()) { //修改的不区分大小写
            return true;
        } else {
            _setCodeStyle("#" + settings.e, settings.codeType.name, settings.codeType.len);
            return false;
        }
    }
};

function _commSetting(option) {
    $.extend(settings, option);
}

function _storeData(dataLabel, data) {
    var store = $(_set.codeval).get(0);
    if (data) {
        $.data(store, dataLabel, data);
    } else {
        return $.data(store, dataLabel);
    }
}

function _setCodeStyle(eid, codeType, codeLength) {
    var codeObj = _createCode(settings.codeType.name, settings.codeType.len);
    var randNum = Math.floor(Math.random() * 6);
    var htmlCode = ''
    if (!settings.inputID) {
        htmlCode = '<span><input id="ehong-code-input" type="text" maxlength="4" /></span>';
    }
    htmlCode += '<div id="ehong-code" class="ehong-idcode-val ehong-idcode-val';
    htmlCode += String(randNum);
    htmlCode += '" href="#" onblur="return false" onfocus="return false" oncontextmenu="return false" onclick="$.idcode.setCode()">' + _setStyle(codeObj) + '</div>' + '<span id="ehong-code-tip-ck" class="ehong-code-val-tip" onclick="$.idcode.setCode()">' + settings.codeTip + '</span>';
    $(eid).html(htmlCode);
    _storeData(_set.storeLable, codeObj);
}

function _setStyle(codeObj) {
    var fnCodeObj = new Array();
    var col = new Array('#BF0C43', '#E69A2A', '#707F02', '#18975F', '#BC3087', '#73C841', '#780320', '#90719B', '#1F72D8', '#D6A03C', '#6B486E', '#243F5F', '#16BDB5');
    var charIndex;
    for (var i = 0; i < codeObj.length; i++) {
        charIndex = Math.floor(Math.random() * col.length);
        fnCodeObj.push('<font color="' + col[charIndex] + '">' + codeObj.charAt(i) + '</font>');
    }
    return fnCodeObj.join('');
}

function _createCode(codeType, codeLength) {
    var codeObj;
    if (codeType == 'follow') {
        codeObj = _createCodeFollow(codeLength);
    } else if (codeType == 'calc') {
        codeObj = _createCodeCalc(codeLength);
    } else {
        codeObj = "";
    }
    return codeObj;
}

function _createCodeCalc(codeLength) {
    var code1, code2, codeResult;
    var selectChar = new Array('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
    var charIndex;
    for (var i = 0; i < codeLength; i++) {
        charIndex = Math.floor(Math.random() * selectChar.length);
        code1 += selectChar[charIndex];

        charIndex = Math.floor(Math.random() * selectChar.length);
        code2 += selectChar[charIndex];
    }
    return [parseInt(code1), parseInt(code2), parseInt(code1) + parseInt(code2)];
}

function _createCodeFollow(codeLength) {
    var code = "";
    var selectChar = new Array('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');

    for (var i = 0; i < codeLength; i++) {
        var charIndex = Math.floor(Math.random() * selectChar.length);
        if (charIndex % 2 == 0) {
            code += selectChar[charIndex].toLowerCase();
        } else {
            code += selectChar[charIndex];
        }
    }
    return code;
}