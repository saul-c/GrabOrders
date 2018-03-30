package cn.lightina.GrabOrders.dao;

import cn.lightina.GrabOrders.pojo.Order;
import cn.lightina.GrabOrders.pojo.SuccessGrabbed;

import java.math.BigInteger;
import java.util.List;

public interface SuccessGrabbedMapper {
    int insertInfo(SuccessGrabbed sg);
    Order queryById(BigInteger orderId,BigInteger userId); //根据id查询成功抢到的订单
    List<SuccessGrabbed> list(); //显示所有成功抢到的订单
}
