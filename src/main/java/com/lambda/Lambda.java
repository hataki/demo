package com.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @Author: hataki
 * @Date: 2020/8/25
 * Time: 10:31
 * description:
 *
 * lambda表达式其实质就是匿名类的一种简写方式，但是要求该类必须有且仅有一个抽象方法
 * 且可以由 @FunctionalInterface 注解来强制声明
 *
 * lambda practice
 *
 * jdk提供的函数式接口：
 *
 * Supplier 代表一个输出
 *
 * Consumer 代表一个输入
 * BiConsumer 代表两个输入
 *
 * Function 代表一个输入，一个输出 *输入输出类型不同*
 * UnaryOperator 代表一个输入，一个输出 *输入输出类型相同*
 *
 * BiFunction 代表两个输入，一个输出 *输入输出类型不同*
 * BinaryOperator 代表两个输入，一个输出 *输入输出类型相同*
 */

public class Lambda {

    public static void main(String[] args) {

        Runnable run1 = ()-> System.out.println("123");
        run1.run();

        /**
         * 函数接口 Function(t,r) -> t是输入，r是输出
         */
        Function<String,Integer> f1 = (str)-> str.length();
        System.out.println("f1.apply(\"1234567\") = " + f1.apply("1234567"));

        /**
         * 函数接口 BiFunction(t1,t2,r) -> t1,t2是输入，r是输出
         * lambda表达式的入参不需要写输出参数，入参为 (a,b) 而不是(a,b,c)
         */
        BiFunction<String,String,Integer> bi = (a,b) -> a.length() + b.length() ;
        System.out.println("bi.apply(\"1\",\"22\") = " + bi.apply("1", "22"));

        /**
         * 方法的引用
         */
        List<String> list = Arrays.asList("h","e","r","o");
        list.forEach(System.out::println);

    }
}
