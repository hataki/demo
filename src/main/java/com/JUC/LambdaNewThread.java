package com.JUC;

/**
 * new a thread though by lambda
 *
 * 既没有继承Thread类也没有实现Runnable接口
 * 但是可以完成一个线程的创建
 *
 * cause Lambda内部实现了匿名内部类的原因
 * Thread类其实是实现了Runnable 接口，所以创建一个线程实质是
 * 实现Runnable(通过各种方法都可以，包括Callable，FutureTask等)
 *
 *  每个线程其实有一个默认的线程名称，是通过构造方法默认实现的
 *  在使用线程池的时候，有必要重新定义线程名称的方法
 *  public Thread() {
 *         init(null, null, "Thread-" + nextThreadNum(), 0);
 *     }
 *
 *
 * Thread中run方法:
 *     @Override
 *     public void run() {
 *         if (target != null) {
 *             target.run();
 *         }
 *     }
 *  target就是你创建线程实现的run方法中的代码
 *  What will be run.
 *  private Runnable target;
 *
 *
 */
public class LambdaNewThread {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            System.err.println("go");
        });

        thread.setName("Thread-x");
        thread.start();
        while(thread.isAlive()){
            System.err.println("not finish ...");
        }
        System.err.println("finish !!...");

        /**
         * 通过输出看出，线程start之后不是立刻马上
         * 就执行run里面的代码的，而是isAlive一段时间
         * 才去真正执行run中的代码
         *
         * 像这种while(true){}在多线程中是非常常见的
         * 线程自旋等待。。。。
         *
         * java创建线程之后，需要向cpu申请资源（底层native实现）
         * 然后绑定系统分配的cpu资源再去执行，中间这个过程
         * 称之为 线程的创建和线程的就绪（等待）
         * 对应到线程池的前2个状态
         *
         * 线程之间切换和分配资源的过程也就是terminated的一个状态
         * cpu资源分配涉及到了信号量和中断，线程上下文，cpu要保存不同线程的
         * 状态，才能切换到下一个线程中去。
         *
         * 线程池核心线程数优化的一个关键点是平稳的保持cpu线程上下文切换
         * -- io密集
         * -- cpu密集
         *
         * output :
         * not finish ...
         * not finish ...
         * not finish ...
         * go
         * not finish ...
         * finish !!...
         */
    }

}
