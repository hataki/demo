# Redis主从复制 Redis Replication

redis默认使用异步主从复制，特点，低延迟和高性能

旧版本replicateof  新版本slaveof  

help slaveof 

```
 SLAVEOF host port
  summary: Make the server a replica of another instance, or promote it as master. Deprecated starting with Redis 5. Use REPLICAOF instead.
  since: 1.0.0
  group: server
```

开启aof之后，redis是不会触碰rdb的，即使开启了混合模式，也只会在aof记录rdb头；

rdb中保存了主从复制的主机的id ，但是aof中不会记录

replicateof no one #取消主从复制

配置

```
replica-serve-stale-data yes  #主从复制中，从服务器可以响应客户端请求；否则，阻塞请求
replica-read-only yes #设置从节点为只读属性
repl-diskless-sync no #是否进行写磁盘，或者直接通过网络io复制 

repl-backlog-size 1mb  #增量复制

min-replicas-to-write 3
min-replicas-max-lag 10 
```

主从复制配置  需要人工维护故障

## Redis哨兵模式——sentinel

redis-server --sentinel

redis-sentinel 

这两个命令都可以启动哨兵模式

通过redis的发布、订阅功能，来发现其他哨兵

当主节点挂掉，从节点会选举出新的主节点，中间会有延迟，以应对存在的网络故障

sentinel.conf 配置文件

quorum 全票数 一般设置2n-1 