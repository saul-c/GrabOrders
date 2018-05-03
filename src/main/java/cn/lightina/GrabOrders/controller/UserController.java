package cn.lightina.GrabOrders.controller;


import cn.lightina.GrabOrders.jwt.Token;
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
    // TODO: 2018/5/3  
    /*@RequestMapping(value = {"/login","/{token}/login"},
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public LoginResult<Token> login(
            @RequestBody User user,
            @PathVariable(value = "token",required = false)Token token){
        LoginResult<Token>lr;
        if(token!=null){
            try {


            }catch (Exception e){
                lr = new LoginResult<>(false, "登陆失败");
            }
        }else {
            try {
                User u = loginService.checkLogin(user);
                if (u == null) return new LoginResult<>(false, "user not found");
                int userId = u.getUserId();
                Token t = new Token(jwtUtil.createToken(user));
                // TODO: 2018/5/2 insert into Token
                lr = new LoginResult<>(true, t);
            } catch (Exception e) {
                lr = new LoginResult<>(false, "登陆失败");
            }
        }
        return lr;
    }*/
}
