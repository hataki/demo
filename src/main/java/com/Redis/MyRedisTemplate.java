package com.Redis;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;



public class MyRedisTemplate {

        public static void main(String args[]){
            ConfigurableApplicationContext ctx = SpringApplication.run(MyRedisTemplate.class);
            MyRedis mr = ctx.getBean(MyRedis.class) ;
        }

}


/**
 * 2个高阶的api
 * redisTemplate 会默认进行序列化
 */
@Component
class MyRedis {

    @Autowired
    private RedisTemplate redisTemplate ;

    @Autowired
    private StringRedisTemplate stringRedisTemplate ;


    private MyRedis(){
        redisTemplate.opsForValue().append("abc" , "1" );
        stringRedisTemplate.opsForValue().append("bbb","1");


        /**
         * 低阶api使用方法
         */
        RedisConnection conn = redisTemplate.getConnectionFactory().getConnection() ;
        conn.set("hello1".getBytes(),"ok !".getBytes());
        System.out.println(conn.get("hello1".getBytes()));

    }


}