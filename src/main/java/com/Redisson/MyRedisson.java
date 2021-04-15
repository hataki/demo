package com.Redisson;

import org.redisson.api.RedissonClient;

import javax.annotation.Resource;
import java.util.function.Supplier;

/**
 * @Author: hataki
 * @Date: 2021/4/15
 * Time: 10:03
 * description:
 *
 */

/**
 * Redisson操作redis
 * Redisson除了提供同步接口外，还提供了异步（Async）、反射式（Reactive）和RxJava2标准的接口。
 * Redisson会序列化java对象然后保存到reids，所以通过redis命令行设置的值，Redisson来获取值会报错；通redis命令行获取Redisson设置的值前面会多出序列化相关的信息
 */
public class MyRedisson {



    @Resource
    private RedissonClient redissonClient ;

    public static void main(String[] args) {

        Supplier<MyRedisson> supplier = MyRedisson::new;
        supplier.get().set("123");

    }


    public void set(String value){

//        RSet<String> set = redissonClient.getSet("user");
//        set.add(value);
        System.err.println(redissonClient.getSet("user"));
    }






}
