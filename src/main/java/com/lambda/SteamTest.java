package com.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Author: hataki
 * @Date: 2020/8/25
 * Time: 16:36
 * description:
 */
public class SteamTest {

    public static void main(String[] args) {
//        generate();

        /**
         * 输出流求偶数的个数  和  sum和
         */
        long count = Arrays.asList(1, 2, 3, 4, 5, 6).stream().filter(x -> x % 2 == 0).mapToInt(x -> x).count();
        System.out.println("count = " + count);
        long sum = Arrays.asList(1, 2, 3, 4, 5, 6).stream().filter(x -> x % 2 == 0).mapToInt(x -> x).sum();
        System.out.println("count = " + sum);
        /**
         * 求最大值
         * max((a, b) -> a - b) 相当于 传入一个表达式a-b和0作比较，如果a-b大于0说明a比b大，反之亦然；
         */
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
//        Optional<Integer> max = list.stream().max((a, b) -> a - b);
        Optional<Integer> max = list.stream().max(Comparator.comparingInt(a -> a));
        System.out.println("max = " + max.get());

        Optional<Integer> min = list.stream().max((a, b) -> b-a);
        System.out.println("min = " + min.get());

    }

    public static void generate(){

        /**
         * generate()方法里面是一个supplier
         */
        Stream<Integer> gen = Stream.generate(()->{
            int i = 0 ;
            System.out.println(i++);
            return i ;
        });
        gen.limit(11).forEach(System.out::println);

        /**
         * 迭代器循环输出
         * iterate(final T seed, final UnaryOperator<T> f)
         * UnaryOperator 输入输出相同类型
         */
        Stream<Integer> iterator = Stream.iterate(1, (x) -> x + 1);
        iterator.limit(11).forEach(System.out::println);
    }
}
