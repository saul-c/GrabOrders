package cn.lightina.GrabOrders.controller;

import cn.lightina.GrabOrders.service.GrabService;
import cn.lightina.GrabOrders.pojo.*;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
@RequestMapping("/graborders")
public class GrabController {
    @Autowired
    GrabService grabService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    List<Order> listOrder(){
        //列表页所需信息
        List<Order>list=grabService.list();

        return list;
    }

    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    GrabResult<Date> getTime(Model model) {
        Date d = new Date();
        GrabResult<Date> gr =new GrabResult<Date>(true,d);
        return gr;
    }

    @RequestMapping(value="/{orderId}/exposer",
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    GrabResult<Exposer>getExposer(@PathVariable("orderId")Integer orderId){
        if(orderId==null)return new GrabResult<Exposer>(false,"orderId为null");
        GrabResult<Exposer>gr;
        try {
            Exposer exposer=grabService.getUrl(orderId);
            gr=new GrabResult<Exposer>(true,exposer);
        }catch (Exception e){
            gr=new GrabResult<Exposer>(false,e.getMessage());
        }
        return gr;
    }

}
