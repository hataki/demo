package com.JUC.BlockQueue;

import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Author: hataki
 * @Date: 2021/4/15
 * Time: 14:07
 * description:
 */
public class BlockingQueueTest {

    static BlockingQueue<MyTask> tasks = new DelayQueue<>();

    static Random r = new Random();

    static class MyTask implements Delayed {
        String name;
        long runningTime;

        MyTask(String name, long rt) {
            this.name = name;
            this.runningTime = rt;
        }

        @Override
        public int compareTo(Delayed o) {
            if(this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
                return -1;
            }
            else if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
                return 1;
            }
            else {
                return 0;
            }
        }

        @Override
        public long getDelay(TimeUnit unit) {

            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }


        @Override
        public String toString() {
            return name + " " + runningTime;
        }
    }

    static class Task implements Runnable{

        long now = System.currentTimeMillis() + 10 ;
        int i ;
        public Task(int i  ) {
             this.i = i ;
        }


        @Override
        public void run() {
            System.err.println(Thread.currentThread().getName() + "put t" + i);
            try {
                Thread.sleep(10);
                i++;
                tasks.put(new MyTask("t"+ i , now + 10*i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        long now = System.currentTimeMillis();
//        MyTask t1 = new MyTask("t1", now + 1000);
//        MyTask t2 = new MyTask("t2", now + 2000);
//        MyTask t3 = new MyTask("t3", now + 1500);
//        MyTask t4 = new MyTask("t4", now + 2500);
//        MyTask t5 = new MyTask("t5", now + 500);
//
//        tasks.put(t1);
//        tasks.put(t2);
//        tasks.put(t3);
//        tasks.put(t4);
//        tasks.put(t5);
//
//        System.out.println(tasks);
//
//        for(int i=0; i<5; i++) {
//            System.out.println(tasks.take());
//        }

        BlockingQueueTest bqt = new BlockingQueueTest();
        bqt.running();


    }

    private BlockingQueueTest(){} ;


    public AsyncTaskExecutor runner(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("My-Executor");
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(50);
        executor.setKeepAliveSeconds(30);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); //对拒绝task的处理策略
        executor.initialize();
        return executor;
    }

    public AsyncTaskExecutor getter(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("get-Executor");
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(50);
        executor.setKeepAliveSeconds(30);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); //对拒绝task的处理策略
        executor.initialize();
        return executor;
    }


    public void running() throws InterruptedException {
        AsyncTaskExecutor executor = runner() ;
        AsyncTaskExecutor getter = runner() ;







//        Thread.sleep(1000);
//        System.out.println(tasks);

        for (int j = 0; j < 100000; j++) {
            executor.execute(new Task(j));
            getter.execute(new Thread(() -> {
                try {
                    System.err.println( tasks.take() );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                }
            }));
        }


    }

}
