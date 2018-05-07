package cn.lightina.GrabOrders.dao;
import cn.lightina.GrabOrders.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User getUserById(int userId);
    int insertUser(@Param("user")User user);
    User checkLogin(@Param("user")User user);
}

