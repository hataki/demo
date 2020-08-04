package com.DesignPatterns.AbstractFactory;

/**
 * @Author: hataki
 * @Date: 2020/8/4
 * Time: 14:48
 * description:
 *
 * how to digest
 *
 */
public class MyMain {
    public static void main(String[] args) throws Exception{

        AbstractFactory af = new MagicFactory();
                Food food =  af.createFood();
                food.printFoodName();
        System.out.println("Level up !! FOod is changing  ");
        Thread.sleep(1000);
                af = new AcientFactory();
                food = af.createFood();
                food.printFoodName();


    }
}
