package cn.lightina.GrabOrders.controller;

import cn.lightina.GrabOrders.service.CategoryService;
import cn.lightina.GrabOrders.pojo.Category;
import cn.lightina.GrabOrders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

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


    @ResponseBody
    @RequestMapping("/submitCategory")
    public String submitCategory(@RequestBody Category category) {
        System.out.println("SSM接受到浏览器提交的json，并转换为Category对象:"+category);
        JSONObject result = new JSONObject();
        result.put("OK", "OK");


        return result.toJSONString();

    }
}
