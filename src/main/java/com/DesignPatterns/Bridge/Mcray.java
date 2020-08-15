package com.DesignPatterns.Bridge;

/**
 * @Author: hataki
 * @Date: 2020/8/15
 * Time: 10:02
 * description:
 */
public class Mcray extends Hero{

    public Mcray(HeroImpl hero){
        this.heroImpl = hero ;
    }

    @Override
    public void printName(String name) {
        super.printName(name);
        System.out.println("i am mcray ! ");
    }
}
