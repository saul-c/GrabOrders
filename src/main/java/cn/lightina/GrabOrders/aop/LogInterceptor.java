package cn.lightina.GrabOrders.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogInterceptor {
    private final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);
    @Before(value = "execution(* cn.lightina.GrabOrders.controller.*.*(..))")
    public void before(JoinPoint joinPoint){
        //logger.info("login start!");
    }
    @After(value = "execution(* cn.lightina.GrabOrders.controller.*.*(..))")
    public void after(){
        //logger.info("login end!");
    }
}
