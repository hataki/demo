# zookeeper

计算机体系

- 冯诺依曼体系
- 麻省（好像是？！）体系

redis  单实例  内存  快 

复制集群 HA sentinel 不保证强一致性，最终一致性难达成

zookeeper是什么

主要是 用来解决  分布式协调



## zookeeper介绍

官网 ： https://zookeeper.apache.org/doc/r3.6.2/zookeeperOver.html

设计目标：

zk简单

zk可以被复制

zk可被订阅

zk速度快

![ZooKeeper Service](https://zookeeper.apache.org/doc/r3.6.2/images/zkservice.jpg)

![ZooKeeper's Hierarchical Namespace](https://zookeeper.apache.org/doc/r3.6.2/images/zknamespace.jpg)

**所有的server 都是一样的 ； 存在leader ，是一种主从集群** 

![ZooKeeper Throughput as the Read-Write Ratio Varies](https://zookeeper.apache.org/doc/r3.6.2/images/zkperfRW-3.2.jpg)

**13个节点 对外提供 10w的请求支撑** 



![Reliability in the Presence of Errors](https://zookeeper.apache.org/doc/r3.6.2/images/zkperfreliability.jpg)

zookeeper存在2中状态： 可用状态  不可用状态

不可用状态 回复 到 可以装填 越快越好 ；

官方压测 为 200ms  

读写分离  read速度是非常快的（类比redis，纯内存，10w per sec ）

## 节点和临时节点

zookeeper 每个节点可以保存 1Mb的数据 （为了快速对外提供 协调数据） 

不用把zk当做数据库使用！！！

**持久节点**：这个1Mb的节点

**临时节点**： 每一个client登录到zk的server时，会创建一个session，类似tomcat；依托于session，

产生临时节点的概念；redis击穿产生的分布式锁问题，就可以通过session的临时节点来控制锁，而不需要额外的代码来维护

**序列节点**

## 特征和保障

ZooKeeper非常快速且非常简单。但是，由于其目标是作为构建更复杂的服务（例如同步）的基础，因此它提供了一组保证。这些是：

- 顺序一致性-来自客户端的更新将按照发送的顺序应用。
- 原子性-更新成功或失败。没有部分结果。
- 单个系统映像-无论客户端连接到哪个服务器，客户端都将看到相同的服务视图。也就是说，即使客户端故障转移到具有相同会话的其他服务器，客户端也永远不会看到系统的较旧视图。
- 可靠性-应用更新后，此更新将一直持续到客户端覆盖更新为止。
- 及时性-确保系统的客户视图在特定时间范围内是最新的。

Sample API

ZooKeeper的设计目标之一是提供一个非常简单的编程界面。因此，它仅支持以下操作：

- *create*：在树中的某个位置创建一个节点
- *delete*：删除节点
- *存在*：测试某个节点是否存在于某个位置
- *获取数据*：从节点读取数据
- *设置数据*：将数据写入节点
- *获取子*节点：检索节点的子节点列表
- *sync*：等待数据传播

## zookeeper安装和配置

不建议安装openjdk 

```
cp zoo_sample.cfg  zoo.cfg 
```

```
tickTime = 2000 #服务生效的间隔是2s

initLimit = 10 #初始化延迟，voter选举leader的延迟为 2s*10= 20s

syncLimit = 5 #同步延时，leader下发操作时，等待2s*5=10s没有回馈 

dataDir = /var/zk #持久化目录，不建议使用tmp，自定义推荐var下

clientPort=2181 #客户端连接的端口号

maxClientCnxns=60 #允许最大连接数

/**

在集群刚启动还没有leader的时候，先通过3888建立socket链接通信，进行选举，选出leader之后，由leader在2888端口，开启集群通讯，与其他server在2888建立链接

server.id 是选举leader的依据，启动数目过半时，最大的leader成为leader ；

启动数目： （server数量 * 2 ）/2 + 1 

*/

server.1=node01:2888:3888 

server.2=node02:2888:3888

server.3=node03:2888:3888

server.4=node04:2888:3888
```

注意： 在每台配置的 dataDir = /var/zk 目录下创建一个 myid ，记录当前 server的id

​             部署集群式，**必须要保持不同的服务器系统时间一致**！！！！

#### 添加zookeeper命令到环境变量

```
vim /etc/profile
export ZOOKEEPER_HOME=/.../zookeeper 
export PATH=$PATH:$ZOOKEEPER_HOME/bin
source /etc/profile

##复制配置文件以及环境变量到其他节点
scp /etc/profile node02:/etc
scp /etc/profile node03:/etc
scp /etc/profile node04:/etc
#所有节点 source profile
#启动zkServer  默认后台启动，但是参数 start-froegroud前台启动
zkServer.sh 
```

设置节点数据

二进制安全（不管客户端的数据编码是什么，zk保存的是二进制格式，同redis）

zk-cli.sh #默认链接localhost的zk

ls /

create /ooxx "" #后面必须要跟“”，否则不会创建节点数据

create /ooxx/ooxx "" 

set/get /ooxx #通过set get 

“hello”

```
cZxid = 0x2000002 #步进器，zk执行按顺序执行，维护一个单机的序列很容易；一共64位，前32位表示leader的纪元（era）,后32位表示事务的序列号
ctime = ***
mZxid = 0x2000006
mtime = ***
pZxid = 0x2000007
cversion = 2 #版本号
dataVersion = 1 
aclVsersion = 0 
ephemeralOwner = 0x0 #临时持有者
dataLength = 7 
numChildren = 2 
```

**节点数据的隔离**

在不同server中创建相同节点名称的节点数据时，会直接覆盖

但是添加 参数 -s 会将数据隔离，不同server同数据key具有不同的数据value 

```
create -s /ooxx "yesssss"
ls /ooxx #会返回2个值，！
```

删除节点  

rmr /ooxx 

## zookeeper 总结

- 统一配置管理 -- 1M数据
- 分组管理 -- Path结构
- 统一命名 -- sequential
- 同步 -- 临时节点 (分布式锁 -- 在父节点通过-s创建不同个 子锁 ，client 实现逻辑控制)

**安装笔记**

```
准备node1~node4
安装jdk，不建议openjdk
下载zookeeper，zookeeper.apache.org
tar -xf zookeeper.*.tar.gz
mkdir /opt/zk
mv zookeeper  /opt/zk
vi /etc/profile 
export ZOOKEEPER_HOME=/.../zookeeper 
export PATH=$PATH:$ZOOKEEPER_HOME/bin
source /etc/profile
cd zookeeper/conf 
cp zoo_sample.cfg  zoo.cfg 
vim zoo.cfg 
mkdir -p /var/zk
echo 1 > /var/zk/myid
...

zkCli.sh
help 
ls / #ls后面必须跟path
create ...

```

netstat -natp | egrep '(2888|3888)'

显示所有 2888 或者 3888 的进程信息

2888： leader接受write请求

3888 ： 投票选举

