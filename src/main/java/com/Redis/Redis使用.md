## Redis基本使用

### String 

（Key -- Value）

- set/get[NX|XX]：添加/获取一个value[NX ：not exist 如果不存在则set | XX ]
- mset/mget：添加/获取多个key值
- append：追加
- getrange：范围
- strlen：返回长度

key可以看做一个object，对应的value可以有以下三种类型

### type：返回value的类型

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