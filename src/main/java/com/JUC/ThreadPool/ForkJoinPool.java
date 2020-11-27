package com.JUC.ThreadPool;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.RecursiveAction;

/**
 * ForkJoinPool
 */
public class ForkJoinPool {
    static int[] nums = new int[1000000] ;
    static final int MAX_NUM = 50000 ;
    static Random r = new Random() ;

    /**
     * 静态代码块
     * Tips ：
     * 静态代码块：用staitc声明，jvm加载类时执行，仅执行一次
     * 构造代码块：类中直接用{}定义，每一次创建对象时执行。
     * 执行顺序优先级：静态块,main(),构造块,构造方法。
     */
    static {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(100);
        }
        System.out.println("---" + Arrays.stream(nums).sum());
    }

    static class AddTask extends RecursiveAction{

        @Override
        protected void compute() {

        }
    }

}
