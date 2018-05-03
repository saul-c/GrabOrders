package cn.lightina.GrabOrders.pojo;

import java.io.Serializable;

public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    private int userId;
    private String userName;
    private String phoneNumber;
    private String passWd;
    private String address;

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassWd() {
        return passWd;
    }

    public void setPassWd(String passWd) {
        this.passWd = passWd;
    }
}
