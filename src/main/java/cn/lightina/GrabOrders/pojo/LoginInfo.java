package cn.lightina.GrabOrders.pojo;

import cn.lightina.GrabOrders.jwt.Token;

public class LoginInfo {
    private User user;
    private Token token;

    public LoginInfo(User user, Token token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
