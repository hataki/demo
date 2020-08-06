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
 * 使用jdk的动态代理  ！！！ 必须要有接口，否则无法代理 ！！！
 *
 * --分离代理行为和被代理对象
 *   --
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
         * 保存中间执行代理类的类文件
         */
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        /**
         * reflection 通过二进制字节码分析类的属性和方法
         *
         *     public static Object newProxyInstance(ClassLoader loader,
         *                                           Class<?>[] interfaces,
         *                                           InvocationHandler h)
         *
         *     -- ClassLoader loader 需要代理的对象
         *     -- Class<?>[] interfaces 要实现的接口数组（代理可以实现多个接口）
         *     InvocationHandler h 方法处理器（被代理对象方法被调用的时候怎么做处理）
         */
        Singing s = (Singing) Proxy.newProxyInstance(DynamicProxy.class.getClassLoader(),
                new Class[]{Singing.class},
                new ProxyHandler<>(dp)
        );

        /**
         * 这个地方调用了接口实现方法，但是同时也调用了handler的invoke方法，如何实现？
         * 动态代理在生成动态代理对象proxy时，传入了interfaces和InvocationHandler参数，
         * 那么代理对象会通过super()自动实现并调用接口方法以及传入的handler的invoke方法
         * 详见 $Proxy0.class
         */
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

    /**
     * 分离代理行为
     */
    public void before() {
        System.out.println("method start..");
    }

    public void after() {
        System.out.println("method stop..");
    }

    /**
     *
     * @param proxy 传入的代理对象 这里是Singing的实例s；注意，这个代理对象是反射生成的代理对象！！！
     * @param method 这是其实就是 Singing实现的singing()方法,因为传入的是泛型，调用传入类的实现方法
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        /**
         * 获取类中所有的方法
         * --直接getClass()获取到当前类的所有方法，相当于this
         */
        Method[] mmm = proxy.getClass().getMethods();
        for(Method m : mmm){
            System.out.println( m.getName() );
        }
        System.out.println("---------------------------");
        Method[] methods = getClass().getMethods();
        for(Method m : methods){
            System.out.println( m.getName() );
        }
        System.out.println("---------------------------");



        before();
        Object o = method.invoke(t , args) ;
        after();
        return o;
    }
}

interface Singing {
    void Singing();
}