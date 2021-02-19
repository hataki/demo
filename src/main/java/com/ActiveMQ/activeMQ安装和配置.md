# activeMQ安装和配置

### 下载

http://activemq.apache.org/

### 启动命令

./activemq start  #启动

./activemq stop   #停止

./activemq start  #查看日志输出

### 修改访问端口

修改 ActiveMQ 配置文件:/usr/local/activemq/conf/jetty.xml

---

***！外部访问时，需要修改host地址为 0.0.0.0*** 

---

**jettyport节点**

配置文件修改完毕，保存并重新启动 ActiveMQ 服务。

### web控制台安全

jetty-realm.properties 文件配置用户名 角色 

```
# username: password [,rolename ...]
admin: admin, admin
user: user, user
yiming: 123, user
```

用户名：密码，角色

注意: 配置需重启ActiveMQ才会生效。

### 消息安全机制

修改 activemq.xml

在123行     </broker> 节点中添加

```xml
	<plugins>
      <simpleAuthenticationPlugin>
          <users>
              <authenticationUser username="admin" password="admin" groups="admins,publishers,consumers"/>
              <authenticationUser username="publisher" password="publisher"  groups="publishers,consumers"/>
              <authenticationUser username="consumer" password="consumer" groups="consumers"/>
              <authenticationUser username="guest" password="guest"  groups="guests"/>
          </users>
      </simpleAuthenticationPlugin>
 </plugins>

```

## 