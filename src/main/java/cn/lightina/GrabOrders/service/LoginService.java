package cn.lightina.GrabOrders.service;

import cn.lightina.GrabOrders.Exception.LoginException;
import cn.lightina.GrabOrders.jwt.Token;
import cn.lightina.GrabOrders.pojo.User;

import java.io.UnsupportedEncodingException;

public interface LoginService {
    User getUserById(int id);
    int addUser(User user);
    User checkLogin(User user);
    User checkToken(Token token)throws UnsupportedEncodingException,LoginException;
    void insertToken(Token token,User user);
}
