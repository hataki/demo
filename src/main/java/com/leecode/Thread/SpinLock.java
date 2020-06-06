package com.leecode.Thread;

/**
 * @Author: wm
 * @Date: 2020/6/5
 * Time: 10:57
 * description:
 */
public class SpinLock {

    enum ReaduToRun {T1,T2};
    static volatile ReaduToRun r = ReaduToRun.T1; //why there must be volatile

    public static void main(String[] args) {

        char[] a1 = "1234567".toCharArray();
        char[] a2 = "ABCDEFG".toCharArray();

        new Thread(() -> {
            for(char c : a1 ){
                while(r != ReaduToRun.T1){}
                System.out.println(c);
                r = ReaduToRun.T2;
            }
        },"t1").start();


        new Thread(() -> {
            for(char c : a2 ){
                while(r != ReaduToRun.T2){}
                System.out.println(c);
                r = ReaduToRun.T1;
            }
        },"t2").start();


    }
}
