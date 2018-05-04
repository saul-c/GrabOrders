package cn.lightina.GrabOrders.jwt;

import cn.lightina.GrabOrders.pojo.User;
import cn.lightina.GrabOrders.redis.TokenRedis;
import com.auth0.jwt.interfaces.Claim;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-mybatis.xml","classpath:spring/spring-source.xml"})
public class JwtUtilTest {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    TokenRedis tokenRedis;

    private String test="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MjU2MTc4MjQsInVzZXJJZCI6MSwiaWF0IjoxNTI1NDQ1MDI0fQ.cuPZ4YOvzZRgg_m9K68C2NT88nQWOzU4s-v4MZcobho";
    @Test
    public void test(){
        User u=new User();
        u.setUserId(1);
        String s=null;
        try {
            s=jwtUtil.createToken(u);
        }catch (Exception e){
            e.printStackTrace();
        }
        tokenRedis.putOrder(new Token(s),1);
        System.out.println("result:");
        System.out.println(s);
        System.out.println("ends");
    }

    @Test
    public void getUser(){
        try {
            Map<String,Claim> map=jwtUtil.verifyToken(test);
            for(String s:map.keySet()){
                System.out.println(s+" "+map.get(s).asInt());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(tokenRedis.getToken(1).getToken());
        System.out.println("ends");
    }
}