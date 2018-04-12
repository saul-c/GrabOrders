package cn.lightina.GrabOrders.service.impl;

import cn.lightina.GrabOrders.dao.UserMapper;
import cn.lightina.GrabOrders.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    @Autowired
    UserMapper um;
    User getUserById(int id){return um.getUserById(id);};
}
