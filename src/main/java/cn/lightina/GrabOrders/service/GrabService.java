package cn.lightina.GrabOrders.service;

import cn.lightina.GrabOrders.pojo.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface GrabService {
    GrabExecution executeGrab(int orderId,int userId,String md5);
    Exposer getUrl(int orderId);
    List<Order> list();
}
