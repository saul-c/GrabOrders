package cn.lightina.GrabOrders.service.impl;

import cn.lightina.GrabOrders.dao.UserMapper;
import cn.lightina.GrabOrders.pojo.User;
import cn.lightina.GrabOrders.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserMapper um;

    public User getUserById(int id){ return um.getUserById(id); }

    public int addUser(User user) { return um.addUser(user); }

    public User checkLogin(User user){
        return um.checkLogin(user);
    }

}
