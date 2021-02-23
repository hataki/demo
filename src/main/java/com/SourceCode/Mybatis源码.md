# Mybatis源码

### 解析配置文件 parseConfiguration

读取xml配置

加载配置 解析外部的properties文件  getResourceAsProperties

getChildProperties 

setting 属性值

设置日志等其他配置

### parse（）解析mapper

有三种解析方式 url class resource ，三只只能取其中的一种方式

### sqlSessionFactory

sqlSessionFactory 实际调用的是 defaultSqlSessionFactory的实现 openSession()

return ：openSessionFromDataSource（configuration.getDefaultExecutorType(),null,false）