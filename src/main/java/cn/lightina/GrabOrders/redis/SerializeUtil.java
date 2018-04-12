package cn.lightina.GrabOrders.redis;


import org.apache.ibatis.javassist.bytecode.ByteArray;

import java.io.*;

public class SerializeUtil {
    public static byte[] serialize(Object object){
        ObjectOutputStream oos=null;
        ByteArrayOutputStream baos=null;
        try {
            baos=new ByteArrayOutputStream();
            oos=new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[]bytes=baos.toByteArray();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Object unserialize(byte[] bytes){
        if(bytes==null)return null;
        ByteArrayInputStream bais=null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
