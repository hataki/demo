package com.leecode.day3;

import java.util.ArrayList;

/**
 * @Author: hataki
 * @Date: 2020/7/8
 * Time: 9:17
 * description: Diving Board LCCI
 *
 * You are building a diving board by placing a bunch of planks of wood end-to-end. There are two types of planks, one of length shorter and one of length longer. You must use exactly K planks of wood. Write a method to generate all possible lengths for the diving board.
 *
 * return all lengths in non-decreasing order.
 *
 * Example:
 *
 * Input:
 * shorter = 1
 * longer = 2
 * k = 3
 * Output:  {3,4,5,6}
 * Note:
 *
 * 0 < shorter <= longer
 * 0 <= k <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/diving-board-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * test result :
 * --> 2ms
 * --> 47.4mb
 *
 * int[] x = new int[0]
 *  the return is {}
 * int[] x = null ;
 *  the return is null ;
 */
public class Question16_11 {

    public static void main(String[] args) {

        Question16_11 q = new Question16_11() ;
        int[] x ;
        x = q.divingBoard(1,2,3) ;
        System.out.println(x);
        x = q.divingBoard(1,1,10000) ;
        System.out.println(x);
        x = q.divingBoard(1,1,0);
        System.out.println(x);

    }

    public int[] divingBoard(int shorter, int longer, int k) {

        if(k <= 0){
            return new int[0] ;
        }
        if(shorter > longer){
            return new int[0] ;
        }
        if(shorter < 0 || longer < 0 ){
            return new int[0] ;
        }
        if(shorter == longer){
            int[] arr = new int[1];
            arr[0] = k*shorter ;
            return arr ;
        }
        /**
         * consider deduplication
         */
        int[] arr = new int[k+1];
        for(int i=0 ; i<k+1 ;i++){
            int sum ;
            sum = shorter * (k-i) + longer * i ;
            arr[i] = sum ;
        }
        return arr ;
    }

}
