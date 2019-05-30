# SpringBoot 打造微信点餐系统

- [SpringBoot 打造微信点餐系统](#springboot-打造微信点餐系统)
    - [项目架构](#项目架构)
    - [项目设计](#项目设计)
        - [角色划分](#角色划分)
        - [功能模块划分](#功能模块划分)
        - [部署架构](#部署架构)
        - [数据库设计](#数据库设计)

    - [开发环境搭建](#开发环境搭建)
    - [功能实现](#功能实现)

### *项目架构*

1. 前端:
   - Vue
   - WebApp

2. 后端:
   - SpringBoot
   - Bootstrap + Freemarker + JQuery

3. 前后端连接:
   - RESTful

4. 数据库:
   - SpringBoot + JPA
   - SpringBoot + MyBatis

5. 缓存:
   - SpringBoot + Redis
      - Redis: 分布式 Session 和 分布式锁
      - 解决`超卖`

6. 消息推送:
   - WebSocket

7. 微信端:
   - 微信扫码登录
   - 消息模板推送
   - 微信支付和退款

### *项目设计*

##### api文档

[api文档](./docs/API.md)

##### [微信](./docs/WeChat.md)

1. 微信授权
2. 微信支付
3. 微信退款

##### 角色划分

1. 买家
2. 卖家

##### 功能模块划分

<div align=center><img src="./docs/E-R.svg" width="60%"></div>

##### 部署架构

微信前端和浏览器请求通过Nginx转发给Tomcat,如果需要缓存,就请求Redis, 否则就请求MySQL

##### 数据库设计

1. 表之间的关系

<div align=center><img src="./docs/E-R1.svg" width="75%" ></div>

2. 具体表

|#|Table|
|:-|:-|
|商品分类表|product_category|
|商品表|product_info|
|订单详情表|order_detail|
|订单主表|order_master|
|卖家信息表|seller_info|

### *开发环境搭建*  

|#|Version|#|Version|
| :- | :- | :- | :- |
|SpringBoot|1.5.20|Linux|CentOS 7.3|
|Idea|2019.1.1|JDK|1.8.0_202|
|Maven|3.3.9|MySQL|5.7.25|
|||Nginx|1.11.7|
|||Redis|3.2.8|

### *日志*

[日志框架](./docs/logger.md)

### *功能实现*

1. 买家端
    - [x] 买家端类目
    - [x] 买家端商品
    - [x] 买家端订单

2. 卖家端
    - [x] 卖家端订单
    - [x] 卖家端通用功能和上下架
    - [x] 卖家端新增商品和类目

3. 微信端
    - [ ] 微信授权
    - [ ] 微信支付和退款

4. 买家和卖家端联通
    - [ ] 分布式session
    - [ ] 卖家信息表
    - [ ] 登陆登出

5. 优化
    - [ ] 异常捕获
    - [ ] MyBatis
    - [ ] 并发
    - [ ] Redis
