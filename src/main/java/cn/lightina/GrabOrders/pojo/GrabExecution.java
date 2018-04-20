package cn.lightina.GrabOrders.pojo;

public class GrabExecution {
    private long orderId;
    private int state;
    //状态的明文标识
    private String stateInfo;

    //秒杀成功的对象回去
    private SuccessGrabbed successGrabbed;
}
