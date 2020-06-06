package com.leecode.Thread;

/**
 * @Author: hataki
 * @Date: 2020/6/6
 * Time: 9:46
 * description:
 */
public class SynchronizedNotify {

    public static void main(String[] args) {

        final Object o = new Object();

        char[] a1 = "1234567".toCharArray();
        char[] a2 = "ABCDEFG".toCharArray();

        new Thread(() -> {
            synchronized (o) {
                for (char c : a1) {
                    try {

                        System.out.println(c);
                        o.notify();
                        o.wait();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                o.notify(); //必须notify，否则程序无法停止。；

            }
        }, "t1").start();


        new Thread(() -> {
            synchronized (o){
                for(char c : a2){
                    try {

                        System.out.println(c);
                        o.notify();
                        o.wait();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                o.notify();
            }
        },"t2").start();


    }

}
