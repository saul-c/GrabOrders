package cn.lightina.GrabOrders.dao;

import cn.lightina.GrabOrders.pojo.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-mybatis.xml"})
public class OrderMapperTest {
    @Autowired
    private OrderMapper orderMapper;
    @Test
    public void reduceNumber() {
        int orderId=6;
        Date date=new Date();
        int updateCount=orderMapper.reduceNumber(orderId,date.getTime());
        System.out.println(updateCount);
    }

    @Test
    public void queryById(){
        int orderId=6;
        Order order1=orderMapper.queryById(orderId);
        System.out.print(order1);
    }
}