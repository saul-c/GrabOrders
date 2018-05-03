package cn.lightina.GrabOrders.redis;

import cn.lightina.GrabOrders.jwt.Token;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import redis.clients.jedis.Jedis;

public class TokenRedis {
    private RuntimeSchema<Token> schema=RuntimeSchema.createFrom(Token.class);

    public Token getToken(int userId){
        Jedis jedis=null;
        try {
            jedis=JedisUtil.getJedis();
            String key="graborders:token:"+userId;
            byte[]value=jedis.get(key.getBytes());
            if(value!=null){
                Token token=schema.newMessage();
                ProtostuffIOUtil.mergeFrom(value,token,schema);
                return token;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return null;
    }
    public String putOrder(Token token,int userId){
        Jedis jedis=null;
        try {
            jedis=JedisUtil.getJedis();
            String key="graborders:token:"+userId;
            byte[]value=ProtostuffIOUtil.toByteArray(token,schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
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
