package cn.lightina.GrabOrders.dao;

import cn.lightina.GrabOrders.pojo.Order;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface OrderMapper {
    int add(Order order); 
    int reduceNumber(BigInteger orderId, Date createTime);
    Order queryById(BigInteger orderId);
    List<Order> list();
}
