一、常识：

磁盘：

1. 寻址：ms
2. 带宽：G/M

内存：

1. 寻址：ns

s > ms > μs > ns

扇区：512Byte

磁盘在寻址上比内存slow less than milion 

数据在 内存 和  磁盘上的体积是不一样的（内存因为指针，体积会小）

二、关系型数据库

- data page 4K 对齐

- 索引、数据 都是存放在磁盘（聚合索引）

- 内存采用B+树

关系型数据库建表： 必须给出schema，

类型：字节宽度给定，

存储：倾向于行级存储；

提问：数据库中，表很大（几个T的数据），性能会下降吗？

ans：如果表有索引，增删改变慢；

查询速度：1，一个或少量查询依然很快；

​					2，并发大的时候会受到硬盘带宽的影响；

三、Redis

nosql ( NOT ONLY SQL ) 可以达到10w/s级别的查询量

！！！ memcached  value没有类型的概念（redis有） ！！！

key <-----> value  

- String

  - 字符类型

  - 数值类型

  - bitmaps

- hashes

- lists

- sets

- sorted sets 

**计算向数据移动**

client ---->  缓存中的k,v 取出v中某一个元素

-->memcached 返回value所有的数据到client server 网络io，client要有你实现的代码去解码

-->redis 类型不是很重要，redis的server对每种类型都有自己的解析index(),lpop; 在server端解析，client直接调用，从而返回少量数据