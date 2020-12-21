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

**对于字符串的数值类型，还可有数值操作**

- incr k1 : k1自增1
- incrby k1 22 ： k1增22
- decr k1：k1减1
- decrby k1 22 ：k1减22

raw  二进制安全  -- redis是没有数据类型的，存储时，客户端必须要一直编码

字符流

字节流  使用字节流存储 √ 

msetnx : 设置多个值，如果没有不存在的（这是一个原子性操作，比如：设置k1,k2如果k1存在，k2不存在，则会失败！）