package com.JUC.ThreadPool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: hataki
 * @Date: 2020/11/28
 * Time: 16:46
 * description:
 */
public class T4_WorkStealingPool {

    public static void main(String[] args) throws IOException {

        /**
         * WorkStealingPool继承子ForkJoinPoolde
         */
        ExecutorService service = Executors.newWorkStealingPool();
        /**
         * 通过Runtime.getRuntime()获取当前所有可用的处理线程
         */
        System.out.println(Runtime.getRuntime().availableProcessors());

        service.execute(new R(1000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000)); //daemon
        service.execute(new R(2000));

        //由于产生的是精灵线程（守护线程、后台线程），主线程不阻塞的话，看不到输出
        System.in.read();

    }

    /**
     * 静态内部类
     *  静态内部类和非静态内部类之间区别:
     *
     * 1. 内部静态类不需要有指向外部类的引用。但非静态内部类需要。
     * 2. 静态类只能访问外部类的静态成员，非静态内部类能够访问外部类的静态和非静态成员。
     * 3. 非静态内部类不能脱离外部类实体被创建，非静态内部类可以访问外部类的数据和方法，因为他就在外部类里面。
     */
    static class R implements Runnable{

        int time ;
        R(int i){
            time = i ;
        }

        @Override
        public void run() {

            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(time  + " " + Thread.currentThread().getName());

        }
    }
}
