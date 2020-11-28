package com.JUC.ThreadPool;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinPool
 */
public class T3_ForkJoinPool {
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

    /**
     * RecursiveAction 递归分支运算，没有返回值；只fork不join
     * compute() 是必须要重写的计算方法
     */
    static class AddTask extends RecursiveAction{

        int start ,end ;

        AddTask(int s,int e){
            start = s ;
            end = e ;
        }
        @Override
        protected void compute() {

            if(end-start <= MAX_NUM){
                long sum = 0L ;
                for (int i = start; i < end; i++) {
                    sum += nums[i] ;
                }
                System.out.println("from " + start + " to: " + end + "= " + sum );
            }else{
                 int mid = start+ (end-start)/2 ;

                 AddTask subTask1 = new AddTask(start,mid);
                 AddTask subTask2 = new AddTask(mid,end);
                 subTask1.fork();
                 subTask2.fork();
            }

        }
    }

    /**
     * RecursiveTask 递归分支运算，具有返回值类型；fork之后，再统一join
     * fork 是异步运算，与主线程并行
     * join 是同步阻塞运算，需要阻塞拿到fork结成的返回结果，
     *      join未拿到结果而主线程结束，会抛出异常
     */
    static class AddTaskReturn extends RecursiveTask<Long> {
        private static final long serialVersionUID = 1L ;
        int start ,end ;

        AddTaskReturn(int s,int e){
            start = s ;
            end = e ;
        }

        @Override
        protected Long compute() {
            if (end - start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                return sum;
            }
            int mid = start + (end - start) / 2;

            AddTaskReturn subTask1 = new AddTaskReturn(start, mid);
            AddTaskReturn subTask2 = new AddTaskReturn(mid, end);
            subTask1.fork();
            subTask2.fork();
            /**
             * 这里手动阻塞
             * join拿不到返回结果，main就会再long result = taskReturn.join();此处同步阻塞住
             */
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return subTask1.join() + subTask2.join();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        T3_ForkJoinPool joinPool = new T3_ForkJoinPool();
        Long start = System.currentTimeMillis();

//        ForkJoinPool forkJoinPool_1 = new ForkJoinPool() ;
//        AddTask task = new AddTask(0,nums.length);
//        forkJoinPool_1.execute(task);
        Long end = System.currentTimeMillis();
//        System.out.println("AddTask use time :" + (end-start));
//        System.out.println("forkJoinPool_1 status :" + forkJoinPool_1.isShutdown());
        ForkJoinPool forkJoinPool_2 = new ForkJoinPool() ;
        AddTaskReturn taskReturn = new AddTaskReturn(0,nums.length);
        forkJoinPool_2.execute(taskReturn);
        long result = taskReturn.join();
        System.out.println(result);
        System.out.println("AddTaskReturn use time :" + (end-start));
        System.out.println("finished!");
        System.out.println("forkJoinPool_1 status :" + forkJoinPool_2.isShutdown());

        /**
         * 自旋 等待fork线程状态；
         * 主线程结束，fork随之结束
         * 主线程没有结束，fork也不会结束，执行完任务之后会回到等待队列
         */
        retry:
        for(;;){
            if(forkJoinPool_2.isShutdown()){
                end = System.currentTimeMillis();
                System.out.println("forkJoinPool_1 status :" + forkJoinPool_2.isShutdown());
                System.out.println("shutDown use time :" + (end-start));
                break retry;
            }
            Thread.sleep(1000);
            System.out.println("fork thread is alive !"  );
//            forkJoinPool_2.shutdown();
        }
        System.out.println("finished!");
    }

}
