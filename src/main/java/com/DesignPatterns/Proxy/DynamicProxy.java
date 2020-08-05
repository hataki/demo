package com.DesignPatterns.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Random;

/**
 * @Author: hataki
 * @Date: 2020/8/5
 * Time: 17:37
 * description:
 *
 * 如果想让LogProxy可以重用，不仅可以代理Tank，还可以代理任何其他可以代理的类型 Object
 * （毕竟日志记录，时间计算是很多方法都需要的东西），这时该怎么做呢？
 * 分离代理行为与被代理对象
 * 使用jdk的动态代理
 */
public class DynamicProxy implements Singing{


    @Override
    public void Singing() {
        System.out.println("lets break it down ! ");
        try {
            Thread.sleep(new Random().nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DynamicProxy dp = new DynamicProxy() ;
        /**
         * reflection 通过二进制字节码分析类的属性和方法
         */
        Singing s = (Singing) Proxy.newProxyInstance(DynamicProxy.class.getClassLoader(),
                new Class[]{Singing.class},
                new ProxyHandler<>(dp)
        );

        s.Singing();

    }
}

/**
 * 动态代理类 handler
 * 使用泛型来获取要动态代理对象类的类型；
 * 再通过反射机制，拿到代理对象的实例；
 * 在代理执行其实现类实现的实例方法。。。
 */
class ProxyHandler<T> implements InvocationHandler {
    T t ;
    public ProxyHandler(T t ){
        this.t = t ;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("method " + method.getName() + "start ..." );
        Object o = method.invoke(t , args) ;
        System.out.println("method " + method.getName() + "end ..." );
        return o;
    }
}

interface Singing {
    void Singing();
}