package cn.lightina.GrabOrders.controller;


import cn.lightina.GrabOrders.jwt.Token;
import cn.lightina.GrabOrders.pojo.LoginResult;
import cn.lightina.GrabOrders.pojo.User;
import cn.lightina.GrabOrders.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/graborders")
public class UserController {
    @Autowired
    private LoginService loginService;


    @RequestMapping(value = "/login",
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public LoginResult<Token> login(@RequestBody User user){
        LoginResult<Token>lr;
        try {
            User u = loginService.checkLogin(user);
            if (u == null) return new LoginResult<>(false, "user not found");
            // TODO: 2018/5/2 get token
            Token t=new Token("todo");
            lr=new LoginResult<>(true,t);
        }catch (Exception e){
            lr=new LoginResult<>(false,"登陆失败");
        }
        return lr;
    }
}
