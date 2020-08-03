package com.DesignPatterns.Strategy;

/**
 * @Author: hataki
 * @Date: 2020/8/3
 * Time: 16:35
 * description:
 * <函数式接口> 需要声明FunctionalInterface（如果只有一个方法可以不用添加该注解）
 * 该接口可以是实现其他n个default方法，但只允许有且仅一个抽象方法
 *
 */
@FunctionalInterface
public interface Comparator<T> {
    int compare(T o1 ,T o2 );

    default void w(){
        System.out.println("w ");
    }

    default void h(){
        System.out.println("h");
    }

    default void y(){
        System.out.println("y ");
    }

}
