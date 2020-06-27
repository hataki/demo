package com.leecode.difficult;

import org.springframework.util.Assert;

import java.util.LinkedList;

/**
 * @Author: hataki
 * @Date: 2020/6/27
 * Time: 12:22
 * description:
 * Given an unsorted integer array, find the smallest missing positive integer.
 *
 * Example 1:
 *
 * Input: [1,2,0]
 * Output: 3
 * Example 2:
 *
 * Input: [3,4,-1,1]
 * Output: 2
 * Example 3:
 *
 * Input: [7,8,9,11,12]
 * Output: 1
 * Note:
 *
 * Your algorithm should run in O(n) time and uses constant extra space.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-missing-positive
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Question41 {

    public static void main(String[] args) {

        int[] x1 ={1,2,0} ;
        int resy1 = 3 ;
        int[] x2 ={3,4,-1,1} ;
        int resy2 = 2 ;
        int[] x3 ={7,8,9,11,12} ;
        int resy3 = 1 ;
        int ss ;

        Question41 q = new Question41() ;

        ss = q.firstMissingPositive(x1);
        System.out.println(ss);

        ss = q.firstMissingPositive(x2);
        System.out.println(ss);

        ss = q.firstMissingPositive(x3);
        System.out.println(ss);


    }

    /**
     *
     * Thinking about the algorithm should run in O(n) time
     * and uses constant extra space ,
     * so i try to use double pointers at first .
     */
    public int firstMissingPositive(int[] nums) {

        int[] x = nums ;
        int result = 1 ;

        for(int i=0 ;i<nums.length;i++){
            int[] y = x ;

            if(x[i] < result){
            }else {
            }

            for(int j =i ;j<nums.length;j++){
                if(y[j] < x[i]){
                    i = j ;
                    result = result < y[j] ? 1 : y[j] ;
                }
            }
        }
        return 0 ;
    }
}
