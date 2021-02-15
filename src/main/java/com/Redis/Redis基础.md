### 一、常识：

磁盘：

1. 寻址：ms
2. 带宽：G/M

内存：

1. 寻址：ns

s > ms > μs > ns

扇区：512Byte

磁盘在寻址上比内存slow less than milion 

数据在 内存 和  磁盘上的体积是不一样的（内存因为指针，体积会小）

### 二、关系型数据库

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

### 三、Redis

nosql ( NOT ONLY SQL ) 可以达到10w/s级别的查询量

**！！！ memcached  value没有类型的概念（redis有） ！！！**

key <-----> value  

- String

  - 字符类型

  - 数值类型

  - bitmaps

- hashes

- lists

- sets

- sorted sets 

#### **计算向数据移动**

client ---->  缓存中的k,v 取出v中某一个元素

-->memcached 返回value所有的数据到client server 网络io，client要有你实现的代码去解码

-->redis 类型不是很重要，redis的server对每种类型都有自己的解析index(),lpop; 在server端解析，client直接调用，从而返回少量数据

### 四、安装Redis

采用编译安装（一般linux下，编译安装的基本都是由c语言所写，需要编译环境gcc等）

命令：make / make install 

（编译/编译并安装，有的在编译安装前需要先执行configure之后才会生成Makefile文件，Makefile中记录make命令，指向的是./src下的Makefile[这个文件里面才是需要执行的全部脚本]，通过查看该脚本可知，make命令其实就是将源码编程可执行程序，安装就是复制编译好的文件到指定目录）

服务安装：使用install_service.sh 

首先需要设置redis的环境变量，/etc/prefile里面；

```
export REDIS_HOME=/../redis5
export PATH=$PATH:$REDIS_HOME/bin
```

source /erc/prefile

- 一个物理机可以通过一份redis程序，安装多个服务，每个服务通过指定不同的端口来区分
- 可执行程序就在同一个目录中，但是内存中未来的多个实例需要各自的配置文件，持久化目录等资源
- service redis_6379 start/stop/status --> linux  /etc/init.d/***

### 五、redis 跳表

```
排序是怎么实现的
增删改查的速度保证
```

#### skip list 

跳表 ---- 平衡树 

![image-20210214174957566](C:\Users\95\AppData\Roaming\Typora\typora-user-images\image-20210214174957566.png)

对于高并发时 ，  增删改平均效率最优 稳定 