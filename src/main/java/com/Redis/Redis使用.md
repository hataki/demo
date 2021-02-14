# Redis基本使用

## String 

通过 help @string/list..查询命令

（Key -- Value）

- set/get[NX|XX]：添加/获取一个value[NX ：not exist 如果不存在则set | XX ]
- mset/mget：添加/获取多个key值（只发送一次请求，降低io）
- append：追加
- getrange：范围
- strlen：返回长度

key可以看做一个object，对应的value可以有以下三种类型

**type：返回value的类型**

embstr 字符串类型

int 数值类型

### 数值

**对于字符串的数值类型，还可有数值操作**

- incr k1 : k1自增1
- incrby k1 22 ： k1增22
- decr k1：k1减1
- decrby k1 22 ：k1减22

raw  二进制安全  -- redis是没有数据类型的，存储时，客户端必须要一直编码

字符流

字节流  使用字节流存储 √ 

msetnx : 设置多个值，如果没有不存在的（这是一个原子性操作，比如：设置k1,k2如果k1存在，k2不存在，则会失败！）

### Bitmap 

setbit key offset value 

offset 位移（bit为8位，位移第几位，从左向右，）

value 值（二进制0或者1）

例： 

setbit kt 1 1 (0100 0000   ASCII-- 40) 

get kt  -- @

setbit kt 7 1 (0100 0000  ASCII -- 41)

get kt -- A

bitpos kt bit start end  [ find first bit set or clear in a string  ]

返回bit在kt中，start到end区间的字节位置，是全量字节的位置（一个字节八位！！）

bitcount  返回当前字节中bit的相对位置

bitop  operation destket key key... [Perform bitwise operations between strings  ]

例：比如k1为A（0100 0001 ） k2为B （0100 0010 ）  

bitop and andkey k1 k2 

A和B 按位与 （有0为0，全0为1 ， 按位或 ：有1为1，全1为0 ！！）

则输出  andkey 为 @（0100 0000）

bitmap 应用 例子一

1.有一个用户系统，统计用户登录天数，且窗口随机

setbit sean 1 1 

setbit sean 7 1 

setbit sean 364 1 

strlen sean 

bitcount sean -2 -1 

例子二

统计用户活跃

使用key来存储日期，value则存储用户通过某种关系转换的映射。

## List

首先想到list是一个链表（不去重！！）

lpush/rpush ： 从左/右放入到list中去

lpop/rpop : 从左/右弹出一个元素

redis的key中会为list自动维护一个 正负索引，同string

lrange

lindex

lrem/linsert key count value...

从左向右按顺序

blpop 阻塞弹出

---

可以描述为 栈stack  -- 同向命令

也可以描述为 队列 -- 反向命令

只操作index（lindex） -- 数组

通过阻塞（blpop） -- 阻塞、单播队列 

---

## hash

key —— value （key--value）相当于value中也存放了一个hashmap

hset/hget : hset user name kitty / hget user name

hmset/hmget : hmset user name kitty age 18 / hmget user name age 

## set

**无序、去重**

集合操作相当多

随机事件：srandmember key count 

- 整数：取出一个去重的结果集（不能超过已有集合）
- 负数：取出一个带重复的结果集，一定会满足你要的数量
- 如果：0 不返回

*比如抽奖的时候，可以使用该命令*

spop 取出一个（*模拟年会抽奖*）

## sorted_set

**list有序不去重，set去重但无序，所以sorted_set具有必要性**

排序既要给出分值也要给出维度（sorted in need）

操作：

```
维度 价格：品种
zadd k1 8 apple 2 banana 3 orange 
zrange k1 0 -1
zrange k1 0 -1 withscores
zrangebyscore k1 3 8
zrange k1 0 1 #价格从低到高
zrevRange k1 0 1 #价格从高到低取出
zrange #按范围 
zscore #按分值
zrank #按排名

zincrby k1 2.5 banana #banana这个价格增加2.5，支持动态排序
zrange k1 0 -1 #使用场景 排行榜，维度播放量下载量等
```

scores：排序所依据的维度

物理内存是：左小右大的顺序摆放方式

物理内存存储顺序，不会随命令而改变

zrange/zrevrange（正反向索引）

zscore 取出key的score

zrank 取出key的排名

具有通用的数学运算

zincrby

！！scores改变之后，会实时维护这个链表的顺序，这点很重要！！

（使用场景：歌曲排行榜，实时投票）

集合操作（交集、并集）

zunionstore [destination numkeys key weight aggregate sum|min|max ] 

```
zadd k1 80 tom 60 l 70 kim 
zadd k2 116 jerry 88 m 18 yiming 
ZUNIONSTORE unkey 2 k1 k2 
zrange unkey 0 -1 withscores
ZUNIONSTORE unkey1 2 k1 k2 weights 1 0.2
zrange unkey1 0 -1 withscores
```

