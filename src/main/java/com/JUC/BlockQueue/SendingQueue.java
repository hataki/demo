package com.JUC.BlockQueue;

import org.springframework.core.task.AsyncTaskExecutor;

public class SendingQueue {

    public static void main(String[] args) {
        BlockingQueueTest b = new BlockingQueueTest();
        AsyncTaskExecutor executor = b.runner() ;

        for (int j = 0; j < 100000; j++) {
            executor.execute(new BlockingQueueTest.Task(j));

        }
    }
}
