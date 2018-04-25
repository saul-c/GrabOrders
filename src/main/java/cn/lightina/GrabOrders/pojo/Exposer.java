package cn.lightina.GrabOrders.pojo;

public class Exposer {
    private boolean exposed;
    private String md5;
    private int orderId;
    private long now;
    private long start;
    private long end;

    public Exposer(boolean exposed,int orderId) {
        this.exposed = exposed;
        this.orderId = orderId;
    }

    public Exposer(boolean exposed, int orderId, long now, long start, long end) {
        this.exposed = exposed;
        this.orderId = orderId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(boolean exposed, String md5, int orderId, long now, long start, long end) {
        this.exposed = exposed;
        this.md5 = md5;
        this.orderId = orderId;
        this.now = now;
        this.start = start;
        this.end = end;
    }
}
