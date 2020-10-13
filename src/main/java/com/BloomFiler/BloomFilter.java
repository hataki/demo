package com.BloomFiler;

/**
 * @Author: hataki
 * @Date: 2020/10/13
 * Time: 15:30
 * description:
 */
public class BloomFilter {

    /**
     * BloomFilter提供了几个重载的静态create方法来创建实例：
     * public static <T> BloomFilter<T> create(Funnel<? super T> funnel, int expectedInsertions, double fpp);
     * public static <T> BloomFilter<T> create(Funnel<? super T> funnel, long expectedInsertions, double fpp);
     * public static <T> BloomFilter<T> create(Funnel<? super T> funnel, int expectedInsertions);
     * public static <T> BloomFilter<T> create(Funnel<? super T> funnel, long expectedInsertions);
     */

    /**
     * static <T> BloomFilter<T> create(Funnel<? super T> funnel, long expectedInsertions, double fpp, Strategy strategy);
     * 参数含义：
     * funnel 指定布隆过滤器中存的是什么类型的数据，有：IntegerFunnel，LongFunnel，StringCharsetFunnel。
     * expectedInsertions 预期需要存储的数据量
     * fpp 误判率，默认是0.03。
     *
     *
     * BloomFilter里byte数组的空间大小由expectedInsertions，fpp参数决定，见方法：
     * static long optimalNumOfBits(long n, double p) {
     *     if (p == 0) {
     *         p = Double.MIN_VALUE;
     *     }
     *     return (long) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
     * }
     * 真正的byte数组维护在类：BitArray中。
     * 最后通过：put和mightContain方法，添加元素和判断元素是否存在。
     * */


}

