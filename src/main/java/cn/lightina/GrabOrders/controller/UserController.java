package cn.lightina.GrabOrders.controller;


import cn.lightina.GrabOrders.pojo.User;
import cn.lightina.GrabOrders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    public ModelAndView listUser(){
        ModelAndView mav=new ModelAndView();
        User temp=new User();
        temp.setUserId(4);
        temp.setUserName("jerry");
        temp.setPassWd("123");
        userService.addUser(temp);
        User user=userService.getUserById(1);
        mav.addObject("user",user);
        mav.setViewName("testUserMapper");
        return mav;
    }


    /*public ModelAndView getUserById(){
        ModelAndView mav=new ModelAndView();
        return mav;
    }*/
}
