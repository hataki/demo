package com.DesignPatterns.AbstractFactory;

/**
 * @Author: hataki
 * @Date: 2020/8/4
 * Time: 10:51
 * description:
 *
 * 抽象工厂类 (不是抽象方法工厂)
 *
 * 动词类 使用接口扩展
 * 名词类 使用抽象类扩展
 *
 * 抽象出一个共同的工厂类，由不同的子实现类去继承并实现需要的方法；
 * 通过创建子实现类实例来具体化功能；
 * 抽象的对象为必备属性，如---food类；
 * 额外属性应使用接口进行扩展；
 *
 */
public abstract class AbstractFactory {


    abstract Food createFood();
    abstract Weapon createWeapon();
    abstract Vehicle createVehicle();

}
