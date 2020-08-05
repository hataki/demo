package com.DesignPatterns.Proxy;

import java.util.Random;

/**
 * @Author: hataki
 * @Date: 2020/8/5
 * Time: 17:24
 * description:
 * 静态代理 -- > 通过代理对象实现接口，以达到来来调用实现类中已经实现的方法。
 */
public class StaticProxy implements Show{


    @Override
    public void show() {
        System.out.println("LEts guess which word is wrong !!!");
        try {
            Thread.sleep(new Random().nextInt(2000));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

//        new ProxyLog(
//                new ProxyTime(
//                        new StaticProxy()
//                )
//        ).show();

        /**
         * equals above
         */
        StaticProxy sp = new StaticProxy() ;
        ProxyTime pt = new ProxyTime(sp) ;
        ProxyLog pl = new ProxyLog(pt) ;
        pl.show();
    }
}

/**
 * 通过接口创建一个其实现的实例，代理这个实例
 */
class ProxyTime implements Show{
    Show s ;

    /**
     * 拿到代理的接口<方法>
     * @param s
     */
    public ProxyTime(Show s ){
        this.s = s ;
    }

    @Override
    public void show() {
        long start = System.currentTimeMillis();
        s.show();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}

class ProxyLog implements Show {
    Show s ;

    public ProxyLog(Show s ){
        this.s = s ;
    }

    @Override
    public void show() {
        System.out.println("show start ");
        s.show();
        System.out.println("show end ");
    }
}

interface Show {
    void show();
}