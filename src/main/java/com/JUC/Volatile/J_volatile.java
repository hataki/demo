package com.JUC.Volatile;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: hataki
 * @Date: 2020/8/3
 * Time: 9:13
 * description:
 */
public class J_volatile {

    void m(){
        System.out.println("aaa");
    }

    public static void main(String[] args) {

        J_volatile j = new J_volatile();

        List<Thread> threadList = new ArrayList<>();
        for(int i = 0;i<threadList.size();i++){
            threadList.add(new Thread( j::m ,"the thread-" + i )) ;

        }
        threadList.forEach((o)-> o.start() );

        threadList.forEach( (o) -> {
            try {
                o.join();
            }catch (Exception e){
                e.printStackTrace();
            }
        });


    }


}
