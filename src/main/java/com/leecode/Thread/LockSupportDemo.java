package com.leecode.Thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author: wm
 * @Date: 2020/6/5
 * Time: 10:28
 * description: 两个线程，交替输出字符显示
 */
public class LockSupportDemo {

    static Thread t1 = null ,t2 = null ;

    public static void main(String[] args) {
        char[] a1 = "1234567".toCharArray();
        char[] a2 = "ABCDEFG".toCharArray();

       t1 = new Thread(() -> {
           for(char c : a1){
               System.out.println(c);
               LockSupport.unpark(t2);
               LockSupport.park();

           }
       },"t1");

        t2 = new Thread(() -> {
            for(char c : a2){
                LockSupport.park();
                System.out.println(c);
                LockSupport.unpark(t1);


            }
        },"t2");

        t1.start();
        t2.start();
    }
}
