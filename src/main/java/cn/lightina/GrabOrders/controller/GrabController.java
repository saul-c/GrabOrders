package cn.lightina.GrabOrders.controller;

import cn.lightina.GrabOrders.service.GrabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/graborder")
public class GrabController {
    @Autowired
    GrabService grabService;

    /*@RequestMapping(value = "/time/now",method = RequestMethod.GET)
    todo


    */
}
