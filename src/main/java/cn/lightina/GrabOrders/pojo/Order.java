package cn.lightina.GrabOrders.pojo;

import java.math.BigInteger;
import java.util.Date;

public class Order {
    private BigInteger orderId; //单一标识
    private String info; //物品信息
    private int number; //库存数量
    private Date createTime; //发布日期
    private Date startTime; //开始时间
    private Date endTime; //结束时间

    public BigInteger getOrderId() {
        return orderId;
    }

    public void setOrderId(BigInteger orderId) {
        this.orderId = orderId;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", info='" + info + '\'' +
                ", number=" + number +
                ", createTime=" + createTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
