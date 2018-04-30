package cn.lightina.GrabOrders.redis;

import cn.lightina.GrabOrders.pojo.Order;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import redis.clients.jedis.Jedis;

public class OrderRedis {
    private RuntimeSchema<Order>schema=RuntimeSchema.createFrom(Order.class);

    public Order getOrder(int orderId){
        Jedis jedis=null;
        try {
            jedis=JedisUtil.getJedis();
            String key="graborders:"+orderId;
            byte[]value=jedis.get(key.getBytes());
            if(value!=null){
                Order order=schema.newMessage();
                ProtostuffIOUtil.mergeFrom(value,order,schema);
                return order;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return null;
    }
    public String putOrder(Order order){
        Jedis jedis=null;
        try {
            jedis=JedisUtil.getJedis();
            String key="graborders:"+order.getOrderId();
            byte[]value=ProtostuffIOUtil.toByteArray(order,schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            String res=jedis.setex(key.getBytes(),1800,value);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return null;
    }
}
