package cn.lightina.GrabOrders.service;
import java.util.List;

import cn.lightina.GrabOrders.pojo.Category;

public interface CategoryService {
    List<Category> list();
    void add(Category c);
}