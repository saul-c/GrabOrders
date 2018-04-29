package cn.lightina.GrabOrders.service.impl;

import cn.lightina.GrabOrders.Exception.GrabException;
import cn.lightina.GrabOrders.Exception.GrabFinishException;
import cn.lightina.GrabOrders.Exception.OrderException;
import cn.lightina.GrabOrders.dao.OrderMapper;
import cn.lightina.GrabOrders.dao.SuccessGrabbedMapper;
import cn.lightina.GrabOrders.pojo.Exposer;
import cn.lightina.GrabOrders.pojo.GrabExecution;
import cn.lightina.GrabOrders.pojo.Order;
import cn.lightina.GrabOrders.pojo.SuccessGrabbed;
import cn.lightina.GrabOrders.service.GrabService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Date;

@Service
public class GrabServiceimpl implements GrabService {
    private String confusion="neverceasetocelebratelife";
    @Autowired
    private OrderMapper ordermapper;

    @Autowired
    private SuccessGrabbedMapper successGrabbedMapper;

    @Transactional
    public GrabExecution executeGrab(int orderId,int userId, String md5) throws OrderException{
        if(md5==null||md5!=getmd5(orderId))throw new OrderException("数据内容被修改");
        Date now=new Date();
        try {
            int surplus = ordermapper.reduceNumber(orderId, now);
            if (surplus <= 0) throw new GrabFinishException("抢单结束");
            SuccessGrabbed sg=new SuccessGrabbed();
            sg.setOrderId(orderId);
            sg.setCreateTime(now);
            sg.setUserId(userId);
            int insertcount=successGrabbedMapper.insertInfo(sg);
            if(insertcount<=0)throw new GrabException("");
            return null;
        }catch (OrderException e1){
            throw e1;
        }catch (GrabFinishException e2){
            throw e2;
        }
    }
    private String getmd5(long orderId) {
        String base=orderId+"/"+confusion;
        String s = DigestUtils.md5Hex(base.getBytes());
        return s;
    }

    public List<Order> list() {
        List<Order>list=ordermapper.list();
        return list;
    }

    public Order queryById(int orderId){
        Order order=ordermapper.queryById(orderId);
        return order;
    }

    @Override
    public Exposer getUrl(int orderId) {
        Order order=ordermapper.queryById(orderId);
        if(order==null)return new Exposer(false,orderId);
        long s=order.getStartTime().getTime();
        long e=order.getEndTime().getTime();
        long now=new Date().getTime();
        if(now<s||now>e)return new Exposer(false,orderId,now,s,e);
        String md5=getmd5(orderId);
        return new Exposer(true,md5,orderId,now,s,e);
    }


}
