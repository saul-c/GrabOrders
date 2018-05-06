package cn.lightina.GrabOrders.dao;

import cn.lightina.GrabOrders.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-mybatis.xml"})
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void checkLogin() {
        User user=new User();
        user.setPhoneNumber("22222222222");
        user.setPassWd("123abc");
        User res=userMapper.checkLogin(user);
        System.out.print(res.getUserId());
    }
}