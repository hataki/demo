package com.JUC.InterviewAlHw;

/**
 * @Author: hataki
 * @Date: 2020/11/19
 * Time: 16:09
 * description:
 */
public class Sol_Synchronized {

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) throws Exception {
        Object o = new Object();
        t1 = new Thread(() -> {
            /**
             * Tips:
             * synchronized 同步锁-->锁的是一个对象 对象头 0 , 1
             * sleep 是当前线程阻塞  wait 让出当前锁,不阻塞线程
             * 二者不是同一概念
             * notify 唤醒线程 -- 不是一定即刻拿到锁，但会拿到锁
             * wait 即刻让出当前锁，进入等待队列等待下一次的notify
             */
            synchronized (o) {
                for (char c : InitCharArray.aI) {
                    System.out.println(c);
                    try {
                        /**
                         * notify 唤醒进入线程等待队列
                         * wait 让出当前这把锁
                         */
                        o.notify();
                        o.wait();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                /**
                 * 这个notify 在synchronized代码块里面，
                 * 拿到当前锁，唤醒后结束程序
                 * 否则程序的2个线程将一直进行等待中
                 */
                o.notify();
            }

        }, "t1");

        t2 = new Thread(() -> {
            synchronized (o) {
                for (char c : InitCharArray.aC) {
                    System.out.println(c);


                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }

        }, "t2");

        t1.start();
        t2.start();
    }

}
