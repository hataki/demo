# Thread

线程，通过实现runnable来执行需要线程执行的方法

```java
public class Thread implements Runnable {
    /* Make sure registerNatives is the first thing <clinit> does. */
    /* native 调用本地方法
     * 通过静态代码块，在线程启动的时候初始化本地方法的调用   
    **/
    private static native void registerNatives();
    static {
        registerNatives();
    }

    private volatile String name;
    private int            priority;
    private Thread         threadQ;
    private long           eetop;
    。。。
    。。。
    。。。
}
```

## 线程状态

- ***NEW 未启动状态***

  java新建一个线程，但是还未等待到cpu分配系统资源时的状态

- ***RUNNABLE 运行状态***

  处于可运行状态的线程正在jvm中执行，也有可能是在jvm中等待cpu的资源

- ***BLOCKED 阻塞状态***

  线程阻塞等待监视器锁定的线程状态，处于synchronized同步代码块中方法被阻塞

- ***WAITING 等待状态***

  等待线程的线程状态；Object.wait() , Thread.join() , LockSupport.park() 方法都会使线程进入等待状态

- ***TIMED_WAITING 具有指定时间的等待状态***

  指定具有等待时间的等待线程的线程状态；

  Thread.sleep、0bject.wait、 Thread.join、 LockSupport.parkNanos、 LockSupport.parkUntil

- ***TERMINATED 已终止线程的线程状态或线程已完成执行***

  终止线程的线程状态。线程正常完成执行或者出现异常。

