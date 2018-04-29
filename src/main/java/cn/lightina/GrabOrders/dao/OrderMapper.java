package cn.lightina.GrabOrders.dao;

import cn.lightina.GrabOrders.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface OrderMapper {
    int add(Order order); //加入新订单
    int reduceNumber(@Param("orderId") int orderId,
                     @Param("nowTime") long nowTime);
    //减少对应订单的库存（默认为1）
    Order queryById(@Param("orderId")int orderId); //根据id查询订单
    List<Order> list(); //显示所有订单
}
