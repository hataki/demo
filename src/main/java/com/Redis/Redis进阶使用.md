# Redis进阶使用

## Redis进阶

推荐学习网站 ：   redis.cn



yum install nc  # 安装linux下的navcat ，使用nc来操作redis

nc loccalhost 6379 #只要redis启动之后，建立链接即可,发行指令就可执行 

```
keys * 
set k1 hello 
```

#### ***补充**：*

*echo -n 不换行输出*

*echo -e 特殊字符处理输出*

*\a 发出警告声；*
*\b 删除前一个字符；*
*\c 最后不加上换行符号；*
*\f 换行但光标仍旧停留在原来的位置；*
*\n 换行且光标移至行首；*
*\r 光标移至行首，但不换行；*
*\t 插入tab；*
*\v 与\f相同；*
*\ 插入\字符；*
*\nnn 插入nnn（八进制）所代表的ASCII字符；*

从文件中导入详见 http://redis.cn/topics/batch-insert.html

### 发布订阅 pub/sub

详见 http://redis.cn/topics/pubsub.html 



### redis事务

详见 http://redis.cn/topics/transactions.html 

用法：

[*MULTI](http://redis.cn/commands/multi.html) 命令用于开启一个事务，它总是返回 `OK` 。 [MULTI](http://redis.cn/commands/multi.html) 执行之后， 客户端可以继续向服务器发送任意多条命令， 这些命令不会立即被执行， 而是被放到一个队列中， 当 [EXEC](http://redis.cn/commands/exec.html)命令被调用时， 所有队列中的命令才会被执行。*

*另一方面， 通过调用 [DISCARD](http://redis.cn/commands/discard.html) ， 客户端可以清空事务队列， 并放弃执行事务。*

##### multi 开启事务

##### exec 执行

##### discard 撤销，执行 [DISCARD](http://redis.cn/commands/discard.html) 命令时， 事务会被放弃， 事务队列会被清空， 并且客户端会从事务状态中退出

##### watch  可以通过乐观锁（optimistic lock）实现 CAS（check-and-set） 操作

为什么redis不支持回滚

以下是这种做法的优点：

- Redis 命令只会因为错误的语法而失败（并且这些问题不能在入队时发现），或是命令用在了错误类型的键上面：这也就是说，从实用性的角度来说，失败的命令是由编程错误造成的，而这些错误应该在开发的过程中被发现，而不应该出现在生产环境中。
- 因为不需要对回滚进行支持，所以 Redis 的内部可以保持简单且快速。

有种观点认为 Redis 处理事务的做法会产生 bug ， 然而需要注意的是， 在通常情况下， 回滚并不能解决编程错误带来的问题。 举个例子， 如果你本来想通过 [INCR](http://redis.cn/commands/incr.html) 命令将键的值加上 1 ， 却不小心加上了 2 ， 又或者对错误类型的键执行了 [INCR](http://redis.cn/commands/incr.html) ， 回滚是没有办法处理这些情况的。

### 布隆过滤器

module -- boolm

基本解决缓存穿透这么个问题

1. ```
   1. 访问redis.io
   2. modules
   3. 访问RedisBloom的github
   4. linux中 使用wget下载
   5. yum install unzip
   6. unzip *.zip
   7. make
   8. cp bloom.so /../redis/
   9. redis-server --loadmodule /../redis/redisbloom.so
   10. redis-cli
   11. bf.add ooxx abc 
   12. bf.exits abc
   13. bf.exits aaa
   ```

   数据库增加元素时，必须要对bloom增加



## Redis作为数据/缓存

redis作为db和作为缓存的区别 ： 

缓存数据不重要，不是全量数据（随访问变化的热点数据）

 ---内存的大小是瓶颈

内存是有限的的，应该淘汰掉冷数据

 --maxmemory <bytes>

--maxmemory-policy noeviction 

LFU 碰撞了多少次

LRU 多久没碰撞了

业务逻辑上面：

控制key的有效期



##### **过期判定原理**

被动访问时判定

周期轮询判定（增量，随机抽取25%判定过期量）

1. ```
   具体就是Redis每秒10次做的事情：
   
   1. 测试随机的20个keys进行相关过期检测。
   2. 删除所有已经过期的keys。
   3. 如果有多于25%的keys过期，重复步奏1.
   ```

   

## 面试常见问题

### 穿透

从业务接收到的查询是你的系统不存在的数据，这样一来就会直接跨过redis缓存去增加数据库压力

解决方案： 

**布隆过滤器**

- client端进行布隆过滤
- 算法放在后端，redis使用bitmap 过滤
- redis直接集成布隆

布隆缺点：只能增加不能删除（换布谷鸟，或者删除的key设置为null）



### 雪崩

类似于击穿（一个key，高并发请求，导致直接越过了redis访问到数据库）

大量的key同时失效，间接造成大量的访问到达数据库

## Redis持久化

#### 持久化机制 RDB

##### linux父子进程

**进程数据是隔离的；**

**父进程可以让子进程看到数据；**

**linux 中export的环境变量，子进程的修改不会破坏父进程数据，同样，父进程的修改也不会破坏子进程**

***涉及另一个知识点： copy on write*** 

***创建子进程时并不发生复制***，而是在修改时才进行复制

  ***好处： 创建子进程速度变快，根据经验，不可能子进程把所有数据都修改一遍***

redis调用了fork出一个子进程，来进行持久化，同时不阻塞redis对外提供服务；

fork出的进程也不会影响redis client



命令行中save触发阻塞持久化

bgsave 后端异步fork子进程持久化

配置文件中给出的save标识其实bgsave



#### 弊端

不支持日期拉链，永远你只有一个dump.rdb

丢失数据相对多

#### 有点

类似于java中的序列化，回复速度快

## Redis管道命令 pipeline

1. 衔接前一个命令的输出 作为后一个命令的输入
2. 管道回触发创建子进程

echo $$ | more  #$$和$BASHPID  都是查看当前进程的pid ， 

echo $BASHPID | more  # $--变量的优先级低于 管道

$$ 高于 |

#### pstree 查看进程树

## Redis的AOF

### redis的写操作记录到文件中

1. 丢失数据少
2. redis中，可以同时开启RDB和AOF，如果开启AOF只会使用AOF恢复，*4.0*以后中包含RDB的全量，增加记录新的写操作；
3. 弊端：体量无限大 --> 恢复慢 ；
   ***日志优化方案***：
   · hdfs，fsimage+edits.log 让日志之记录增量合并的过程
   · 4.0 Before，重写，删除抵消的命令，合并重复的命令，最终也是一个纯命令的日志文件
   · 4.0 After ， 重写，讲辣的数据RDB到aof文件中，将增量的以指令的方式Append到AOF中，得到一个混合体（利用了RDB的速度，快；同时利用了日志的全量）

**redis --> kernel fd8 buffer (内核会开辟一个buffer缓冲区) --> device** 

redis是内存型数据库  -->  写操作会触发IO  -->  NO:AWAYS 每秒

AOF 默认关闭 ，修改配置文件中的 

```
appendonly yes 
appendfilename "appendonly.aof"

auto-aof-rewrite-percentage 100 
auto-aof-rewrite-min-size 64mb 
```

三个级别

appendfsync always #每次都直接写buffer，最可靠，最多丢失一条数据

appendfsync everysec #每一秒写一次buffer

appendfsync no #（其实就是内核的写buffer）不主动调取flash，buffer满的时候写入磁盘，缺点就是会丢失一个buffer大小的数据