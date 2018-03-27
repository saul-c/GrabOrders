package cn.lightina.GrabOrders.service.impl;

import cn.lightina.GrabOrders.pojo.Category;
import cn.lightina.GrabOrders.service.CategoryService;
import cn.lightina.GrabOrders.dao.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryMapper cm;
    public List<Category> list() {
        return cm.list();
    }
}
