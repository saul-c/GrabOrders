package cn.lightina.GrabOrders.dao;
import cn.lightina.GrabOrders.pojo.Category;

import java.util.List;

public interface CategoryMapper {
    public int add(Category category);

    public void delete(int id);

    public Category get(int id);

    public int update(Category category);

    public List<Category> list();

    public int count();

}
