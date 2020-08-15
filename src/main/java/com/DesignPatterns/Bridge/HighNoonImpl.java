package com.DesignPatterns.Bridge;

/**
 * @Author: hataki
 * @Date: 2020/8/15
 * Time: 10:03
 * description:
 */
public class HighNoonImpl extends HeroImpl{
    @Override
    public void shwo() {
        super.shwo();
        System.out.println("its high noon !");
    }
}
