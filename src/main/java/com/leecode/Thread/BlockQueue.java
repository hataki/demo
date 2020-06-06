package com.leecode.Thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author: hataki
 * @Date: 2020/6/5
 * Time: 19:35
 * description:
 */
public class BlockQueue {

    static BlockingQueue<String> bq1 = new ArrayBlockingQueue<>(1);
    static BlockingQueue<String> bq2 = new ArrayBlockingQueue<>(1);

    public static void main(String[] args) {
        char[] a1 = "1234567".toCharArray();
        char[] a2 = "ABCDEFG".toCharArray();

        new Thread(() -> {
            for(char c : a1){
                System.out.println(c);
                try{
                    bq1.put("ok");
                    bq2.take();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"t1").start();


        new Thread(() -> {
            for(char c : a1){
                System.out.println(c);
                try{
                    //bq2.put("ok");
                    bq2.put("ok");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"t2").start();
    }

}
