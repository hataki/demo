package com.DesignPatterns.Builder;

/**
 * @Author: hataki
 * @Date: 2020/8/13
 * Time: 10:42
 * description:
 * 构造器对象
 *   -- 内部类模拟实现需要构造类
 *
 */
public class HighNoon {

    Target target ;
    Vision vision ;
    Scope  scope ;
}

class Target{
    int count ;
    boolean res ;

    public Target(int count , boolean res ){
        this.count = count ;
        this.res = res ;
    }

}

class Vision {
    int x,y,w,h ;

    public Vision(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
}

class Scope {

    int x,y,w,h ;

    public Scope(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
}