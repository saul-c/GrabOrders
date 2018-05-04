<%--
  Created by IntelliJ IDEA.
  User: chenzhihao
  Date: 2018/4/28
  Time: 下午5:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cn.lightina.GrabOrders.pojo.Order"%>
<html>
<head>
    <title>订单详情页</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="/lib/css/countdown.css" rel="stylesheet">
</head>
<body>

<%Order order=(Order)request.getAttribute("order");%>
<div class="container">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title" style="text-align:center;"><%=order.getInfo()%></h3>
        </div>
    </div>
    <div class="show-product" align="center">
        <div class="time-item">
            <strong>抢购倒计时:</strong>
            <strong id="day_show">0天</strong>
            <strong id="hour_show">0时</strong>
            <strong id="minute_show">0分</strong>
            <strong id="second_show">0秒</strong>
        </div>
    </div>
    <div align="center" id="message"></div>
    <div align="center" style="margin-top:30px;"><button class="btn btn-primary btn-lg" id="btn" disabled="disabled">立即抢购</button></div>
</div>



<script type="text/javascript" src="/lib/js/graborders.js"></script>
<script>
    $(function () {
        graborders.orderdetail.init({
            orderId:${order.orderId},
            startTime: ${order.startTime.time},
            endTime: ${order.endTime.time}
        });
    })
</script>
</body>
</html>
