package com.JUC.BlockQueue;

import org.springframework.core.task.AsyncTaskExecutor;

public class GettingQueue {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueueTest b = new BlockingQueueTest();
        AsyncTaskExecutor getter = b.getter() ;
        boolean re = false ;
        while(re) {

//            getter.execute(new Thread(() -> {
            BlockingQueueTest.MyTask myTask = b.tasks.take();
//            }));
        }
    }
}
