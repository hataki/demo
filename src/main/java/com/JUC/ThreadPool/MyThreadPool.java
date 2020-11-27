package com.JUC.ThreadPool;

import java.util.concurrent.*;

/**
 * @Author: hataki
 * @Date: 2020/11/27
 * Time: 15:24
 * description:
 */
public class MyThreadPool {

    public static void main(String[] args) throws ExecutionException,InterruptedException {

        MyThreadPool myThreadPool = new MyThreadPool() ;
        myThreadPool.MyCallable();

    }


    /**
     * 重写Callable 方法；
     * Callable本质是一个实现了具有返回值的Runnable接口！
     */
    public void MyCallable() throws ExecutionException, InterruptedException {
        Callable<String> c = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hello Callable ! ";
            }
        };

        /**
         * 自定义创建一个标准线程池
         * new ThreadPoolExecutor()
         * 线程池7个参数
         *  int corePoolSize,   核心线程数
         *  int maximumPoolSize,  普通线程数
         *  long keepAliveTime,  超时时间
         *  TimeUnit unit,   超时时间单位 TimeUnit.SECONDS
         *  BlockingQueue<Runnable> workQueue, 组的队列
         *  ThreadFactory threadFactory,  线程工厂
         *  RejectedExecutionHandler handler,  拒绝策略
         *
         *  通过Executors 工具类创建一个jdk自带的线程池
         *  --ali不允许使用自带线程池
         *  --因为自带线程池的最大job数为  Integer.MAX_VALUE
         *  --会产生溢出风险
         */

        ExecutorService service = Executors.newCachedThreadPool();
        /**
         * 异步提交，线程池中线程开始执行
         * Future 实现了Callable接口的实现类，用来获取Callable的返回值
         * future.get()是一个阻塞方法！
         */
        Future<String> future = service.submit(c);
        System.err.println(future.get());
        service.shutdown();
    }

}
