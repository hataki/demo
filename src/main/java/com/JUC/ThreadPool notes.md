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
* private static final int RUNNING    = -1 << COUNT_BITS;
* private static final int SHUTDOWN   =  0 << COUNT_BITS;
* private static final int STOP       =  1 << COUNT_BITS;
* private static final int TIDYING    =  2 << COUNT_BITS;
* private static final int TERMINATED =  3 << COUNT_BITS;
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
* RejectedExecutionHandler handler,  拒绝策略

jdk自带的拒绝策略有四种：

- #### AbortPolicy

ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。

- #### DiscardPolicy

ThreadPoolExecutor.DiscardPolicy：丢弃任务，但是不抛出异常。如果线程队列已满，则后续提交的任务都会被丢弃，且是静默丢弃。

- #### DiscardOldestPolicy

ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新提交被拒绝的任务。

- #### CallerRunsPolicy

ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务，当所有线程都在执行任务时，不执行抛弃，直接由正在进行的线程直接去执行；

```
main Task 62   //所有线程都忙碌，由main去执行
go 60--- 1618295485531
cip-Executor3 Task 62
cip-Executor5 Task 62
cip-Executor1 Task 62
cip-Executor2 Task 62
cip-Executor4 Task 62
```

