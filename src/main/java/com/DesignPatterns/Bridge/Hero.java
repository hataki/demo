package com.DesignPatterns.Bridge;

/**
 * @Author: hataki
 * @Date: 2020/8/15
 * Time: 9:56
 * description:
 */
public abstract class Hero {
    /**
     * 聚合实现类
     */
    HeroImpl heroImpl ;

    public void printName(String name ){
        System.out.println(name + " is getting up ! ");
    }
}
