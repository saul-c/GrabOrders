package cn.lightina.GrabOrders.dao;
import cn.lightina.GrabOrders.pojo.User;
public interface UserMapper {
    User getUserById(int userId);
    int addUser(User user);
    User checkLogin(User user);
}
