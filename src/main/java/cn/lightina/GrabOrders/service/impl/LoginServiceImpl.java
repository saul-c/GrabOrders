package cn.lightina.GrabOrders.service.impl;

import cn.lightina.GrabOrders.Exception.LoginException;
import cn.lightina.GrabOrders.Exception.TokenInvalidException;
import cn.lightina.GrabOrders.Exception.UserNotFoundException;
import cn.lightina.GrabOrders.dao.UserMapper;
import cn.lightina.GrabOrders.jwt.JwtUtil;
import cn.lightina.GrabOrders.jwt.Token;
import cn.lightina.GrabOrders.pojo.User;
import cn.lightina.GrabOrders.redis.TokenRedis;
import cn.lightina.GrabOrders.service.LoginService;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserMapper um;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenRedis tokenRedis;

    public User getUserById(int id){ return um.getUserById(id); }

    public int insertUser(User user) { return um.insertUser(user); }

    @Override
    public User checkToken(Token token) throws LoginException,UnsupportedEncodingException{
        try {
            Map<String,Claim> map=jwtUtil.verifyToken(token.getToken());
            Integer userId=map.get("userId").asInt();
            Token valid=tokenRedis.getToken(userId);
            if(valid==null||!valid.getToken().equals(token.getToken()))
                throw new TokenInvalidException("token失效");
            User user=um.getUserById(userId);
            if(user==null)throw new UserNotFoundException("用户不存在");
            return user;
        }catch (TokenInvalidException e1){
            throw e1;
        }catch (UserNotFoundException e2){
            throw e2;
        }catch (UnsupportedEncodingException e3) {
            throw e3;
        }
    }

    public User checkLogin(User user){
        return um.checkLogin(user);
    }

    @Override
    public void insertToken(Token token,User user) {
        try {
            tokenRedis.putToken(token,user.getUserId());
        }catch (Exception e){
            throw e;
        }
    }
}
