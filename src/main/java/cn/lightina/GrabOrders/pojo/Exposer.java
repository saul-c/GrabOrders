package cn.lightina.GrabOrders.pojo;

public class Exposer {
    private boolean exposed;
    private String md5;
    private int orderId;
    private long nowTime;
    private long startTime;
    private long endTime;

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public long getNowTime() {
        return nowTime;
    }

    public void setNowTime(long nowTime) {
        this.nowTime = nowTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public Exposer(boolean exposed, int orderId) {
        this.exposed = exposed;
        this.orderId = orderId;
    }

    public Exposer(boolean exposed, int orderId, long nowTime, long startTime, long endTime) {
        this.exposed = exposed;
        this.orderId = orderId;
        this.nowTime = nowTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Exposer(boolean exposed, String md5, int orderId, long nowTime, long startTime, long endTime) {
        this.exposed = exposed;
        this.md5 = md5;
        this.orderId = orderId;
        this.nowTime = nowTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
