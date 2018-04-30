package cn.lightina.GrabOrders.redis;

import cn.lightina.GrabOrders.dao.OrderMapper;
import cn.lightina.GrabOrders.pojo.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-mybatis.xml","classpath:spring/spring-redis.xml"})
public class OrderRedisTest {
    private int orderId=6;
    @Autowired
    OrderRedis orderRedis;
    @Autowired
    OrderMapper orderMapper;

    @Test
    public void getOrder() {
        Order order=orderRedis.getOrder(orderId);
        if(order==null){
            Order order1=orderMapper.queryById(orderId);
            if(order1!=null){
                String res=orderRedis.putOrder(order1);
                System.out.println("put "+res);
                Order order2=orderRedis.getOrder(orderId);
                System.out.println(order2);
            }
        }else{
            System.out.print("get");
        }
    }

    @Test
    public void putOrder() {
    }
}