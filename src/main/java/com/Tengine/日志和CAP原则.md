## 日志

## 异常控制/错误编码

细分异常，针对不同代码可能发生的异常及时抛出并记录日志，一旦发现问题可以快速定位。

永远不存在没有bug的系统，也没有永远不出bug的系统，要未雨绸缪，早期就安排上日志，一旦出错，有备无患！

### 异步请求/同步请求

那些业务走异步，那些走同步

业务保障： 99.9999% ，俗称**6个9**

### ACID特性

即 原子性、一致性、隔离性、持久性

### CAP原则

对于分布式系统架构，cap是必备原则

cap又被称作 **布鲁尔定理** 

cap定义 在高并发的场景下的取舍，大型集群中区分容错很难保证，一旦要确保容错性，那么就会损失数据的一直行和高可用性！

#### Consistency  一致性

【--copy 大法：：：--】

G1，G2为数据节点，同时存储了键值对 key=v:value=0

![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap7.svg)

:o: 上图：**向G1 写入数据** 

![img](D:/马士兵/InternetArchitect/12 亿级流量多级缓存高并发系统架构实战/images/cap8.svg)![img](D:/马士兵/InternetArchitect/12 亿级流量多级缓存高并发系统架构实战/images/cap9.svg)

:o: 上图：数据写入完成，G1->v:1，写入完成后，在向G1读取数据的时候就会得到v:1，此时是一致性



![img](D:/马士兵/InternetArchitect/12 亿级流量多级缓存高并发系统架构实战/images/cap10.svg  )![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap11.svg)

:o: 上图：那么此时如果向G2发起读请求的话，因为数据没有同步，就会得到v:0,此时数据不一致



![img](D:/马士兵/InternetArchitect/12 亿级流量多级缓存高并发系统架构实战/images/cap12.svg)![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap13.svg)![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap14.svg)![img](https://mwhittaker.github.io/blog/an_illustrated_proof_of_the_cap_theorem/assets/cap15.svg)







![img](D:/马士兵/InternetArchitect/12 亿级流量多级缓存高并发系统架构实战/images/cap16.svg)![img](D:/马士兵/InternetArchitect/12 亿级流量多级缓存高并发系统架构实战/images/cap17.svg)![img](D:/马士兵/InternetArchitect/12 亿级流量多级缓存高并发系统架构实战/images/cap18.svg)![img](D:/马士兵/InternetArchitect/12 亿级流量多级缓存高并发系统架构实战/images/cap19.svg):o: 上图：如果流程变成这样的

- 写入G1
- G1向G2同步数据
- **等待**同步完成
- 通知写完成
- 读取数据

似乎得到了一致性

一致性是指分布式系统中，数据在多节点存在副本，那么数据如果**一直不修改**，在读的时候是不存在问题的，你访问哪个节点的数据都一样

可一旦要是发生了**修改**，那么数据同步无法在修改的**瞬间**广播到所有副本节点

那么在读的时候就可能发生**数据脏读**



脑裂

#### 数据库

item_id = 100,谁在改，时间

单点/AP

#### Redis 高性能

A/P

#### Zookeeper

##### 饥渴式

![1570811505077](D:/马士兵/InternetArchitect/12 亿级流量多级缓存高并发系统架构实战/images/1570811442275.png)



##### **懒汉式**

![1570811535035](D:/马士兵/InternetArchitect/12 亿级流量多级缓存高并发系统架构实战/images/1570811535035.png)



**数据篡改**

拜占庭将军问题



分布式系统 一致性问题

- 强一致性

- 弱一致性

- 最终一致性



CAP中的一致性