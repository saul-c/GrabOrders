package cn.lightina.GrabOrders.service;

import cn.lightina.GrabOrders.pojo.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface GrabService {
    GrabExecution executeGrab(int orderId,int userId,String md5);

}
