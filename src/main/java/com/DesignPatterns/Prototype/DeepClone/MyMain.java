package com.DesignPatterns.Prototype.DeepClone;

/**
 * @Author: hataki
 * @Date: 2020/8/17
 * Time: 10:41
 * description:
 * 深克隆
 * ！！克隆的对象需要重写clone方法！！
 * ！！对象内属性需要重写clone方法！！
 * 不需要对String进行深克隆（因为String使用的是常量池）
 *
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
    public Object clone() throws CloneNotSupportedException {
        Persn p = (Persn)super.clone();
        p.loc = (Location)loc.clone();
        return p;
    }
}

class Location implements Cloneable {
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

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
