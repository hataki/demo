package com.JUC.BlockQueue;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

public class MyBlockingQueue  extends DelayQueue {

    public MyBlockingQueue(){
        DelayQueue d = new DelayQueue();

        System.err.println(d.size());
    }

    public static void main(String[] args) {
        MyBlockingQueue m = new MyBlockingQueue();

    }
}
