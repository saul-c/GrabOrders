package cn.lightina.GrabOrders.pojo;

import java.math.BigInteger;
import java.util.Date;

public class SuccessGrabbed {
    private int userId; //用户唯一标识符
    private int orderId; //物品唯一标识符
    private Date createTime; //抢单创建时间

    public SuccessGrabbed(int orderId, int userId, Date createTime) {
        this.userId = userId;
        this.orderId = orderId;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SuccessGrabbed{" +
                "userId=" + userId +
                ", orderId=" + orderId +
                ", createTime=" + createTime +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
