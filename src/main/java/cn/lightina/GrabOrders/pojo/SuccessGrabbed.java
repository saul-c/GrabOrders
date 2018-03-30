package cn.lightina.GrabOrders.pojo;

import java.math.BigInteger;
import java.util.Date;

public class SuccessGrabbed {
    private BigInteger userId; //用户唯一标识符
    private BigInteger orderId; //物品唯一标识符
    private Date createTime; //抢单创建时间

    @Override
    public String toString() {
        return "SuccessGrabbed{" +
                "userId=" + userId +
                ", orderId=" + orderId +
                ", createTime=" + createTime +
                '}';
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public BigInteger getOrderId() {
        return orderId;
    }

    public void setOrderId(BigInteger orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
