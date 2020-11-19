package com.JUC.InterviewAlHw;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author: hataki
 * @Date: 2020/11/19
 * Time: 11:16
 * description:
 */
@SuppressWarnings({"Duplicates"})
public class Sol_LockSupport {


    static Thread t1 = null , t2 = null ;

    public static void main(String[] args) throws Exception{

        t1 = new Thread(()->{
            for (char c : InitCharArray.aI) {
                System.out.println(c);
                /**
                 * 这一块的顺序是不能颠倒的，
                 * 必须是先唤醒t2线程，在阻塞t1线程；
                 * 因为park()阻塞当前线程之后，就不会再执行下一条语句了
                 */
                LockSupport.unpark(t2);
                LockSupport.park();

            }
        },"t1");

        t2 = new Thread(()->{
            for (char c : InitCharArray.aC) {
                /**
                 * 这块顺序也是不可颠倒的
                 * 先阻塞t1线程，不再输出，
                 * t2输出完之后，在阻塞t2
                 */
                LockSupport.park();
                System.out.println(c);
                LockSupport.unpark(t1);

            }
        },"t2");



        t1.start();
        t2.start();

    }



}
