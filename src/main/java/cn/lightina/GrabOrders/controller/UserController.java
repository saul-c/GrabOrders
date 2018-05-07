package cn.lightina.GrabOrders.controller;


import cn.lightina.GrabOrders.jwt.JwtUtil;
import cn.lightina.GrabOrders.jwt.Token;
import cn.lightina.GrabOrders.pojo.LoginInfo;
import cn.lightina.GrabOrders.pojo.LoginResult;
import cn.lightina.GrabOrders.pojo.User;
import cn.lightina.GrabOrders.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/graborders")
public class UserController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = {"/login","/{token}/login"},
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public LoginResult<LoginInfo> login(
            @RequestBody(required = false) User user,
            @PathVariable(value = "token",required = false)String tokenString){
        LoginResult<LoginInfo>lr;
        if(tokenString!=null){
            /*有Token 检验Token*/
            Token token=new Token(tokenString);
            try {
                User u=loginService.checkToken(token);
                /*隐藏关键信息*/
                u.setUserId(-1);
                u.setPassWd(null);
                lr = new LoginResult<>(true, new LoginInfo(u,token));
            }catch (Exception e){
                lr = new LoginResult<>(false, "登陆失败");
            }
        }else {
            /*没有Token 验证用户并获取Token*/
            try {
                if(user==null)return new LoginResult<>(false, "user error");
                User u = loginService.checkLogin(user);
                if (u == null) return new LoginResult<>(false, "user not found");
                int userId = u.getUserId();
                Token t = new Token(jwtUtil.createToken(u));
                loginService.insertToken(t,u);
                /*隐藏关键信息*/
                u.setUserId(-1);
                u.setPassWd(null);
                lr = new LoginResult<>(true, new LoginInfo(u,t));
            } catch (Exception e) {
                lr = new LoginResult<>(false, "登陆失败");
            }
        }
        return lr;
    }

    // TODO: 2018/5/5 register
    
}
