package com.DesignPatterns.AbstractFactory;

/**
 * @Author: hataki
 * @Date: 2020/8/4
 * Time: 14:36
 * description:
 */
public class MagicFactory extends AbstractFactory{

    @Override
    Food createFood() {
        return new Mushroom();
    }

    @Override
    Weapon createWeapon() {
        return null;
    }

    @Override
    Vehicle createVehicle() {
        return null;
    }
}
