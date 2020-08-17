package com.DesignPatterns.Prototype.ShallowClone;

/**
 * @Author: hataki
 * @Date: 2020/8/17
 * Time: 10:31
 * description:
 * 浅克隆
 * 指针引用 他们都指向了同一个对象。对象内部的属性地址是一摸一样的；
 *
 */
public class MyMain {

    public static void main(String[] args) throws CloneNotSupportedException {

        Persn p1 = new Persn();
        Persn p2 = (Persn) p1.clone();

        System.out.println(p2.name + "  " + p2.age);
        System.out.println(p2.loc);

        System.out.println(p1.loc == p2.loc);
        p1.loc.latitude = 11 ;
        p1.loc.longitude = 11 ;
        System.out.println(p2.loc);

    }
}

class Persn implements Cloneable {
    int age = 18 ;
    String name = "forever" ;

    Location loc = new Location(32,32);

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Location {
    int longitude ;
    int latitude ;

    public Location(int longitude, int latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Location{" +
                "longitude='" + longitude + '\'' +
                ", latitude=" + latitude +
                '}';
    }
}
