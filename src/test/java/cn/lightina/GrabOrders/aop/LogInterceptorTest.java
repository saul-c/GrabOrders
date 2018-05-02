package cn.lightina.GrabOrders.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-mybatis.xml"})
public class LogInterceptorTest {
    private final Logger logger =LoggerFactory.
            getLogger(LogInterceptorTest.class);

    public void print(){
        System.out.println("test start");
        logger.trace("trace level");
        logger.debug("debug level");
        logger.info("info level");
        logger.warn("warn level");
        logger.error("error level");
        System.out.println("test end");
    }

    @Test
    public void test(){
        LogInterceptorTest lt=new LogInterceptorTest();
        lt.print();
    }

    public int ex(){
        int b=111;
        try{
            b=2/0;
            return 222;
        }catch (Exception e){
            e.printStackTrace();
            return 333;
        }finally {
            System.out.println('h');
            return 444;
        }
    }

    @Test
    public void s(){
        int c=ex();
        System.out.println(c);
    }
}