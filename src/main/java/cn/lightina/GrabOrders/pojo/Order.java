package cn.lightina.GrabOrders.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigInteger;
import java.util.Date;
public class Order {
    private int orderId; //单一标识
    private String info; //物品信息
    private int number; //库存数量
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime; //发布日期
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date startTime; //开始时间
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date endTime; //结束时间

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getInfo() {
        return info;
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
