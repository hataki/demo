package com.lambda;

/**
 * @Author: hataki
 * @Date: 2021/4/15
 * Time: 10:34
 * description:
 */
public class MyCar {

    /**
     * 私有化无参/default构造方法
     */
    private MyCar(){}


    private MyCar(int i){}


    /**
     * 提供不同构造方法
     */
    public MyCar getMyFirstCar(){
        return new MyCar();
    }

}
