package cn.lightina.GrabOrders.service.impl;

import cn.lightina.GrabOrders.dao.UserMapper;
import cn.lightina.GrabOrders.pojo.User;
import cn.lightina.GrabOrders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper um;
    public User getUserById(int id){return um.getUserById(id);}

    public void addUser(User user) { um.addUser(user); }

}
