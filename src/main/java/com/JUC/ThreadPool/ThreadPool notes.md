### 基础知识
集合 ： Map collection queue
queue 队列 （kafka，rocketmq[message queue]）

cas(compare and swap)自旋锁 和 volatile

#### 线程池
1.ThreadPoolExecutor<br>
2.ForkJoinPool

### 源码解析
1. ctl,可以看作一个int型数字，高三位标识线程池状态，低29位表示worker数量
2. COUNT_BITS Integer.SIZE 为32，所以it为29
3. CAPACITY，线程池允许的最大线程数量。1左移29位，然后-1，即 2^29 -1 
4. 线程池有5种状态，按大小排序： RUNNING < SHUTDOWN < STOP < TIDYING < TERMINATED
private static final int RUNNING    = -1 << COUNT_BITS;
private static final int SHUTDOWN   =  0 << COUNT_BITS;
private static final int STOP       =  1 << COUNT_BITS;
private static final int TIDYING    =  2 << COUNT_BITS;
private static final int TERMINATED =  3 << COUNT_BITS;
5. runStateOf() 获取线程池状态，通过按位与操作，低29位将全部变成0
6. workCountOf() 获取线程池worker的数量，通过按位与操作，高三位将变成0 
7. ctlOf() 根据线程池状态和线程池worker数量，生成ctl值


```Callable
Callable本质是一个实现了具有返回值的Runnable接口！
```


### ThreadPool
ExcutorService (类似Collectors的集合工具类)创建一个标准的线程池<br/>
ThreadPool 核心的7个参数：<br>
* int corePoolSize,   核心线程数
* int maximumPoolSize,  普通线程数
*  long keepAliveTime,  超时时间
*  TimeUnit unit,   超时时间单位 TimeUnit.SECONDS
*  BlockingQueue<Runnable> workQueue, 组的队列
*  ThreadFactory threadFactory,  线程工厂
*  RejectedExecutionHandler handler,  拒绝策略
 jdk默认自带的拒绝策略有4种