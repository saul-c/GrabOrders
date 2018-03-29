package cn.lightina.GrabOrders.controller;

import cn.lightina.GrabOrders.service.CategoryService;
import cn.lightina.GrabOrders.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class CategoryController {
    static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/listCategory")
    public ModelAndView listCategory(){
        ModelAndView mav = new ModelAndView();
        List<Category> cs= categoryService.list();
        //List<Category>cs=new ArrayList<Category>();
        Category temp=new Category();
        cs.add(temp);
        // 放入转发参数
        mav.addObject("cs", cs);
        // 放入jsp路径
        mav.setViewName("listCategory");
        return mav;
    }
    @RequestMapping("/submitCategory")
    public String submitCategory(){
        System.out.print("444");
        return "listCategory";
    }
    /*@ResponseBody
    @RequestMapping("/submitCategory")
    public String submitCategory(@RequestBody Category category){
        categoryService.add(category);
        return "ok";
    }*/
}
