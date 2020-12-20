epoll \- I/O event notification facility

epoll属于linux内核的一个**新的系统调用**主要是为了应对高并发场景下，替代select和poll的线性复杂模型；epoll的时间复杂度为O(1)

通过**`/proc/sys/fs/epoll/max_user_watches`**

可以查看当前系统所支持最大文件描述符的数量！

epoll函数：

epoll_create:创建一个epoll实例，文件描述符（fd）

epoll_ctl:将监听的fd添加到epoll实例中，

epoll_wait:灯带epoll事件在实例中发生，并返回对应文件描述符

file descriptor 文件描述符

Redis

--单进程，单线程，单实例

当请求并发很多的时候，如何变得很快？？

redis 和 kernel 之间使用的是 epoll （多路复用）

引出：nginx --> 多少颗cpu启动多少个进程worker --> kernel的epoll （同步，非阻塞多路复用）



命令参数

-p 指定端口号

-n 指定db，redis在内存中默认分为16个库（0，1是固定，其余默认为2-16），相互之间隔离数据不可见。



key <--> value 

key：每一个key都是一个对象，里面包括key，type:value , encoding ,length ....目的是可以减少深度查询，在某些场景下可以，server不去查询value的值而直接获得value的属性，比如计算长度，encoding计算表达式等，在一定程度上提高了redis效率；

value：String（其实可以理解为Byte）

字符串：set/get/append/setrange/getrange/strlen

数值：incr --> [场景]抢购、秒杀、详情页规避并发下，对数据库的事务操作完全由redis内存计算代替

bitmap：setbit/bitcount/bitops/bitop --> 每日签到

