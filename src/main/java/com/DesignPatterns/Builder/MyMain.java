package com.DesignPatterns.Builder;

/**
 * @Author: hataki
 * @Date: 2020/8/13
 * Time: 10:36
 * description:
 */
public class MyMain {

    public static void main(String[] args) {
        /**
         * example 1
         * 通过构造器外部初始换数据
         */
        OverWatcher ow = new OverWatcher.OverWatchBuilder()
                .builderName("麦克雷")
                .builderRole("输出")
                .builderWeapon("左轮")
                .builderSkill("123")
                .build();
        System.out.println(ow.toString());
        /**
         * example 2
         * 直接在构造器内部初始化固定的数据
         */
        HighNoonBuilder builder = new HighNoonImpBuilder();
        HighNoon highNoon = builder.buildTarget().buildVision().buildScope().build();
        System.out.println(highNoon.toString());

    }
}
