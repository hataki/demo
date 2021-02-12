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

### ！！Redis 无法做到强一致性 ！！

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

##### 拜占庭将军问题

含义是在存在消息丢失的不可靠信道上试图通过消息传递的方式达到一致性是不可能的 

“拜占庭将军问题”延伸到互联网生活中来，其内涵可概括为：在互联网大背景下，当需要与不熟悉的对方进行价值交换活动时，人们如何才能防止不会被其中的恶意破坏者欺骗、迷惑从而作出错误的决策。进一步将“拜占庭将军问题”延伸到技术领域中来，其内涵可概括为：在缺少可信任的中央节点和可信任的通道的情况下，分布在网络中的各个节点应如何达成共识 【百度】



分布式系统 一致性问题

- 强一致性

- 弱一致性

- 最终一致性



CAP中的一致性

#### Availability 可用性

#### Partition tolerance 分区容错性

分区容错 无法达到100% 

##### CP

不同空间中的数据，如果要求他们所有状态一致，则必然不在同一时间。

##### AP

不同空间中，如果要求同一时间都可以从任意的空间拿到数据，则必然数据的状态不一致。

##### CA

不同空间的数据，如果要求任意时间都可以从任意空间拿到状态一致的数据，则空间数必然为1.

### Zookeeper和Eureka

#### **zookeepr**

保证**CP**，即任何时刻对zookeeper的访问请求能得到一致性的数据结果，同时系统对网络分割具备容错性，但是它不能保证每次服务的可用性。从实际情况来分析，在使用zookeeper获取服务列表时，如果zk正在选举或者zk集群中半数以上的机器不可用，那么将无法获取数据。所以说，**zk不能保证服务可用性**。

#### eureka

保证**AP**，eureka在设计时优先保证可用性，每一个节点都是平等的，一部分节点挂掉不会影响到正常节点的工作，不会出现类似zk的选举leader的过程，客户端发现向某个节点注册或连接失败，会自动切换到其他的节点，只要有一台eureka存在，就可以保证整个服务处在可用状态，只不过有可能这个服务上的信息并**不是最新的信息**。

## BASE 理论

eBay的架构师Dan Pritchett**源于对大规模分布式系统的实践总结**，在ACM上发表文章提出BASE理论，BASE理论是对CAP理论的延伸，核心思想是即使无法做到强一致性（StrongConsistency，CAP的一致性就是强一致性），但应用可以采用适合的方式达到最终一致性（Eventual Consitency）。

```
ACM国际大学生程序设计竞赛（英文全称：ACM International Collegiate Programming Contest（简称ACM-ICPC或ICPC））
```

### Basically Available

**基本可用**

在分布式系统出现故障的时候，允许损失部分可用性,支持分区失败，即保证核心可用。

##### Soft State

**软状态**

接受一段时间的状态不同步，及中间状态，而改中间状态不影响系统整体可用性。这里的中间状态就是CAP理论中的数据不一致性。

##### Eventually Consistent

**最终一致性**

上面说软状态，然后不可能一直是软状态，必须有个时间期限。在期限过后系统能够保证在没有其他新的更新操作的情况下，数据最终一定能够达到一致的状态，因此所有客户端对系统的数据访问最终都能够获取到最新的值。

强一致性



## 补充

ap 和 cp 重在理解

cap 不能同时存在

关于 partition 分区之理解 ：

