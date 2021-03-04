package com.JUC.ThreadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyNewThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.shutdown();
        /**
         * 马上关闭
         * executorService.shutdownNow();
         */

        executorService.awaitTermination(1, TimeUnit.MILLISECONDS);
    }
}
