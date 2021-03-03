# eureka源码

为什么可以变成eureka server ？ 

1.@EnableEurekaServer 注解里面，只有一个class marker {}类。作为标记使用

2.在引入的pom依赖中的spring.factories中，配置了EurekaServerAutoConfiguration ，这个类中，有一个注解 @ConditionalOnBean({Marker.class})当condition中存在这个bean的时候，才可以把eurekaServer注入进来





安全保护机制  多节点（10++）关闭

启动的时候，心跳是通过timer来实现的（timer有一个弊端，一组中一个抛出异常，整租timer就挂掉了）