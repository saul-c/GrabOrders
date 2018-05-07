# GrabOrders
### 简介
一个基于Spring+SpringMVC+Mybatis+Redis做二级缓存+Maven管理的抢单系统<br>
目标优化高并发场景<br>

### 使用技术
	本项目所选用的全部技术框架如下表
后端 | ... 
:---:|:---
核心框架 | spring、springmvc、mybatis
连接池 | Alibaba Druid
缓存框架 | Redis
权限框架 | JWT
图片验证码(支持gif) | (待使用)[EasyCaptcha](https://github.com/whvcse/EasyCaptcha)
密码加密 | (待使用)[EndecryptUtil](https://github.com/whvcse/EndecryptUtil)

前端 | ... 
:---:|:---
核心框架(轻量简洁) | [jQuery](http://jquery.cuishifeng.cn/)
路由框架 | (待使用) [Angular.js](http://www.angular.cnn/)

### 目录结构
```
.
├── README.md
├── src
│   ├── main
│   │   ├── java
│   │   │   └── cn
│   │   │       └── lightina
│   │   │           └── GrabOrders
│   │   │               ├── Exception (异常类)
│   │   │               ├── aop	(登陆日志切面 not applied)
│   │   │               │   └── LogInterceptor.java
│   │   │               ├── controller
│   │   │               │   ├── GrabController.java (抢单)
│   │   │               │   └── UserController.java (登陆/注册)
│   │   │               ├── dao
│   │   │               │   ├── OrderMapper.java
│   │   │               │   ├── SuccessGrabbedMapper.java
│   │   │               │   └── UserMapper.java
│   │   │               ├── jwt
│   │   │               │   ├── JwtUtil.java (登陆Token的工具类)
│   │   │               │   └── Token.java
│   │   │               ├── pojo
│   │   │               │   ├── Exposer.java (服务器端验证后返回的抢单借口)
│   │   │               │   ├── GrabResult.java （ResponseBody包装抢单结果类)
│   │   │               │   ├── LoginInfo.java
│   │   │               │   ├── LoginResult.java （ResponseBody包装登陆结果类)
│   │   │               │   ├── Order.java (订单信息pojo)
│   │   │               │   ├── SuccessGrabbed.java
│   │   │               │   └── User.java (用户信息pojo)
│   │   │               ├── redis
│   │   │               │   ├── JedisUtil.java (Redis工具类)
│   │   │               │   ├── OrderRedis.java (缓存订单)
│   │   │               │   ├── RedisCache.java (待删除)
│   │   │               │   ├── SerializeUtil.java (待删除)
│   │   │               │   └── TokenRedis.java (缓存token)
│   │   │               └── service
│   │   │                   ├── GrabService.java
│   │   │                   ├── LoginService.java
│   │   │                   └── impl
│   │   │                       ├── GrabServiceimpl.java (抢单业务逻辑)
│   │   │                       └── LoginServiceImpl.java (登陆业务逻辑)
│   │   ├── resources
│   │   │   ├── Mapper
│   │   │   │   ├── Order.xml
│   │   │   │   ├── User.xml
│   │   │   │   └── successGrabbed.xml
│   │   │   ├── log4j2.xml
│   │   │   ├── mybatis-config.xml
│   │   │   ├── spring
│   │   │   │   ├── spring-mybatis.xml (spring与mybatis结合的配置 包括连接池)
│   │   │   │   └── spring-source.xml (托管service和一些bean)
│   │   │   └── springMVC.xml
│   │   └── webapp
│   │       ├── WEB-INF
│   │       │   ├── orderdetail.jsp (还未前后端分离 待updating)
│   │       │   └── web.xml
│   │       ├── index.html (抢单主界面)
│   │       └── lib
│   │           ├── css
│   │           ├── images
│   │           └── js
│   └── test
```

主界面:
![主界面](https://github.com/jacklightChen/GrabOrders/blob/master/src/main/webapp/lib/images/graborders_intro4.png)
登陆/注册界面:
![注册界面](https://github.com/jacklightChen/GrabOrders/blob/master/src/main/webapp/lib/images/graborders_intro5.png)
抢购界面:
![抢购界面1](https://github.com/jacklightChen/GrabOrders/blob/master/src/main/webapp/lib/images/graborders_intro2.png)
![抢购界面2](https://github.com/jacklightChen/GrabOrders/blob/master/src/main/webapp/lib/images/graborders_intro3.png)