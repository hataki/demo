package com.DesignPatterns.AbstractFactory;

/**
 * @Author: hataki
 * @Date: 2020/8/4
 * Time: 14:41
 * description:
 */
public class AcientFactory extends AbstractFactory {


    @Override
    Food createFood() {
        return new Banana();
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
