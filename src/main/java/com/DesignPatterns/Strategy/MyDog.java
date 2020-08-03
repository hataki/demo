package com.DesignPatterns.Strategy;

/**
 * @Author: hataki
 * @Date: 2020/8/3
 * Time: 10:48
 * description:
 */
public class MyDog implements Comparable {

    int food = 0  ;

    public MyDog(int food ){
        this.food = food ;
    }


    @Override
    public int comparable(Object o) {
        return 0;
    }
}
