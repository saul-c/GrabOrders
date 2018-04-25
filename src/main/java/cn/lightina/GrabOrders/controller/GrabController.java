package cn.lightina.GrabOrders.controller;

import cn.lightina.GrabOrders.service.GrabService;
import cn.lightina.GrabOrders.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
@RequestMapping("/graborder")
public class GrabController {
    @Autowired
    GrabService grabService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    String listOrder(Model model){
        List<Order>list=grabService.list();
        model.addAttribute("list",list);
        return "list";
    }

    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    GrabResult<Date> getTime(Model model) {
        Date d = new Date();
        GrabResult<Date> gr =new GrabResult<>();
        gr.setData(d);
        return gr;
    }



}
