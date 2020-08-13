package com.DesignPatterns.Builder;

/**
 * @Author: hataki
 * @Date: 2020/8/13
 * Time: 10:20
 * description:
 * 通过构造器直接创建并初始化对象
 */
public class OverWatcher {

    String name ;
    String weapon ;
    String role ;
    String skill ;

    private OverWatcher(){};

    public static class OverWatchBuilder{

        OverWatcher ow = new OverWatcher() ;

        public OverWatchBuilder builderName(String name){
            ow.name = name ;
            return this ;
        }

        public OverWatchBuilder builderWeapon(String weapon){
            ow.weapon = weapon ;
            return this ;
        }

        public OverWatchBuilder builderRole(String role){
            ow.role = role ;
            return this ;
        }

        public OverWatchBuilder builderSkill(String skill){
            ow.skill = skill ;
            return this ;
        }

        public OverWatcher build(){
            return ow ;
        }

    }


}
