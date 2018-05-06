package cn.lightina.GrabOrders.dao;
import cn.lightina.GrabOrders.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User getUserById(int userId);
    int addUser(User user);
    User checkLogin(@Param("user")User user);
}

