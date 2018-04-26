package cn.lightina.GrabOrders.pojo;

public class GrabResult<T> {
    boolean success;
    T data;
    String errorinfo;

    public GrabResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public GrabResult(boolean success, String errorinfo) {
        this.success = success;
        this.errorinfo = errorinfo;
    }
}
