package com.JUC.RefTypeAndThreadPool;

/**
 * @Author: hataki
 * @Date: 2020/12/3
 * Time: 18:23
 * description:
 * 重写了Object的finalize()方法
 */
public class M {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("This is rewrite finalize !");
    }
}
