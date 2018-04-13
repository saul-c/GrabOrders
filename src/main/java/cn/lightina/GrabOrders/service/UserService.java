package cn.lightina.GrabOrders.service;

import cn.lightina.GrabOrders.pojo.User;

public interface UserService {
    User getUserById(int id);
    void addUser(User user);
}
