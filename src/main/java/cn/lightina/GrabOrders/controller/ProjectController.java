package cn.lightina.GrabOrders.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import cn.lightina.GrabOrders.pojo.Product;
@Controller
public class ProjectController{
   @RequestMapping("/add")
    public ModelAndView change(Product product) throws Exception{
        ModelAndView mv=new ModelAndView("showProject.jsp");
        return mv;
    }
}
