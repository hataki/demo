### 基础知识
集合 ： Map collection queue
queue 队列 （kafka，rocketmq[message queue]）






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