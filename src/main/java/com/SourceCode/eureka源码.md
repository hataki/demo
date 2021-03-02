# eureka源码

为什么可以变成eureka server ？ 

1.@EnableEurekaServer 注解里面，只有一个class marker {}类。作为标记使用

2.在引入的pom依赖中的spring.factories中，配置了EurekaServerAutoConfiguration ，这个类中，有一个注解 @ConditionalOnBean({Marker.class})当condition中存在这个bean的时候，才可以把eurekaServer注入进来