package cn.lightina.GrabOrders.dao;

import cn.lightina.GrabOrders.pojo.SuccessGrabbed;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-mybatis.xml"})
public class SuccessGrabbedMapperTest {
    @Autowired
    SuccessGrabbedMapper successGrabbedMapper;
    @Test
    public void insertInfo() {
        Date d=new Date();
        SuccessGrabbed sg=new SuccessGrabbed(1,1,d);
        successGrabbedMapper.insertInfo(sg);
    }
}