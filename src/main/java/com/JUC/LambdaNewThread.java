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
