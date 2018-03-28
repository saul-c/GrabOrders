package cn.lightina.GrabOrders.controller;

import cn.lightina.GrabOrders.service.CategoryService;
import cn.lightina.GrabOrders.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping("listCategory")
    public ModelAndView listCategory(){
        ModelAndView mav = new ModelAndView();
        List<Category> cs= categoryService.list();
        //List<Category>cs=new ArrayList<Category>();
        Category temp=new Category();
        temp.setId(1);
        temp.setName("aaa");
        cs.add(temp);
        // 放入转发参数
        mav.addObject("cs", cs);
        // 放入jsp路径
        mav.setViewName("listCategory");
        return mav;
    }
}
