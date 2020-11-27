package com.JUC.ThreadPool;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: hataki
 * @Date: 2020/11/27
 * Time: 16:21
 * description:
 */
public class HelloThreadPool {


    static class Task implements Runnable{
        private int i;

        public Task(int i ){
            this.i = i ;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Task " + i );
            try{
                /**
                 * 阻塞住该线程的执行
                 */
                System.in.read();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Task{" +
                    "i=" + i +
                    "}";
        }
    }

    /**
     *
     * @param args
     * ThreadPoolExecutor.CallerRunsPolicy() 抛弃策略 :
     *   当线程池满了之后，其余的任务会直接抛回给任务请求者去执行
     *   下例就是main线程执行
     */
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,4,0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 8   ; i++) {
            executor.execute(new Task(i));
        }

        System.out.println(executor.getQueue());

        executor.execute(new Task(100));

        System.out.println(executor.getQueue());

        executor.shutdown();

    }
}
