package cn.lightina.GrabOrders.pojo;

import java.io.Serializable;

public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    private int userId;
    private String userName;
    private String passWd;
    User(){}
    User(int userId,String userName,String passWd){
        this.userId=userId;
        this.userName=userName;
        this.passWd=passWd;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWd() {
        return passWd;
    }

    public void setPassWd(String passWd) {
        this.passWd = passWd;
    }
}
