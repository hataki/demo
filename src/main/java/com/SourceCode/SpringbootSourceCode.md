### Springboot 源码学习笔记
---
<Observer >
1. EventPublishingRunListener 从spring.factory配置文件中加载到11个监听器
在程序开始的时候先写入到缓存中；

applicationStartingEvent 

2. 首先会将和application启动相关的4个监听器进行实例化，并
初始化当前environment（日志system/etc...）
3. 再次会加载默认的环境配置configurationProperties、

applicationEnvironmentPrepareEvent
开始准备环境配置
ConfigurableEnvironment environment = prepareEnvironment(listeners , applicationArguments)

propertiesSourceList {
serveletCongig
serverletContextInit
systemPropertites
systemEnvironment
}

4. ApplicationPrepareEvent

- prepareContext 
- refreshContext (the core method in Spring source)

---
---
### 自动装配
@EnableAutoConfiguration
-->@AutoConfigurationPackage 
   --> @Import(AutoConfigurationPackages.Registrar.class)
        Registrar一个注册器
-->@Import(AutoConfigurationImportSelector.class) 
   自动装配的关键类 (选择pom文件中引入的starter并进行自动装配)
   
   
SpringDataApplication 
ConfigurationClassParser //Parse each @Configuration class
--> 在这个类 解析添加了配置注解的类


<重要> onRefresh方法
    -- createWebServer()方法 装入嵌入的web容器
    finishRefresh方法
    -- startWebServer()方法 启动装入的tomcat
    
    Tomcat tomcat = new Tomcat() ; 
    setEngine,setServer,setService 通过设置tomcat需要的属性
    initialize() ;
    