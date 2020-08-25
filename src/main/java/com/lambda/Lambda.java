package com.lambda;

import java.util.function.Function;

/**
 * @Author: hataki
 * @Date: 2020/8/25
 * Time: 10:31
 * description:
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


    }
}
