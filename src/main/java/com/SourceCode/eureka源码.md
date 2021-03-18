# eureka源码

为什么可以变成eureka server ？ 

1.@EnableEurekaServer 注解里面，只有一个class marker {}类。作为标记使用

2.在引入的pom依赖中的spring.factories中，配置了EurekaServerAutoConfiguration ，这个类中，有一个注解 @ConditionalOnBean({Marker.class})当condition中存在这个bean的时候，才可以把eurekaServer注入进来





安全保护机制  多节点（10++）关闭

启动的时候，心跳是通过timer来实现的（timer有一个弊端，一组中一个抛出异常，整租timer就挂掉了）



## CAP

eureka 符合ap原则（zookeeper是cp）

## 三级缓存

## 优化

```
  server:
  	# 自我保护，看服务多少。
    enable-self-preservation: false
    # 自我保护阈值
    renewal-percent-threshold: 0.85
    # 剔除服务时间间隔
    eviction-interval-timer-in-ms: 1000
    # 关闭从readOnly读注册表
    use-read-only-response-cache: false
    # readWrite 和 readOnly 同步时间间隔。
    response-cache-update-interval-ms: 1000
```

