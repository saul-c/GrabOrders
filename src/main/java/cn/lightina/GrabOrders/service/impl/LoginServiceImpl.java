package cn.lightina.GrabOrders.service.impl;

import cn.lightina.GrabOrders.Exception.LoginException;
import cn.lightina.GrabOrders.Exception.TokenInvalidException;
import cn.lightina.GrabOrders.dao.UserMapper;
import cn.lightina.GrabOrders.jwt.JwtUtil;
import cn.lightina.GrabOrders.jwt.Token;
import cn.lightina.GrabOrders.pojo.User;
import cn.lightina.GrabOrders.redis.TokenRedis;
import cn.lightina.GrabOrders.service.LoginService;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public int addUser(User user) { return um.addUser(user); }

    @Override
    public User checkToken(Token token) throws LoginException{
        try {
            Map<String,Claim> map=jwtUtil.verifyToken(token.getToken());
            Integer userId=map.get("userId").asInt();
            
        }catch (TokenInvalidException e1){
            throw new TokenInvalidException("token失效");
        }catch (Exception e2){

        }

        return null;
    }

    public User checkLogin(User user){
        return um.checkLogin(user);
    }

}
