package cn.lightina.GrabOrders.pojo;

public class GrabResult<T> {
    private boolean success;
    private T data;
    private String errorinfo;

    public GrabResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public GrabResult(boolean success, String errorinfo) {
        this.success = success;
        this.errorinfo = errorinfo;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorinfo() {
        return errorinfo;
    }

    public void setErrorinfo(String errorinfo) {
        this.errorinfo = errorinfo;
    }
}
