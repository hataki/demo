package com.DesignPatterns.Bridge;

/**
 * @Author: hataki
 * @Date: 2020/8/15
 * Time: 14:46
 * description:
 *
 * Hero 是一个顶级抽象类，聚合了一个HeroImpl的实现类；
 * Mcray 继承了Hero，同时传入一个具体的实现类（HeroImpl，HighNoonImpl。。。）
 * 桥接模式的体现：可以自由的在 抽象子类 与 具体实现子类 间互相搭配
 *
 * for Example :
 * 英雄 麦克雷 裂空 黑百合 安娜 ，都可以实现相同的方法开枪，也同时可以具备给自独特的大招 。
 *
 *
 */
public class Mymain {

    public static void main(String[] args) {

    Hero m = new Mcray(new HighNoonImpl());

    m.heroImpl.shwo();
    m.printName("麦克雷");





    }


}
