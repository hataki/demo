package com.leecode.day4;

import com.sun.org.apache.xerces.internal.impl.dv.dtd.NMTOKENDatatypeValidator;

import java.util.HashMap;

/**
 * @Author: hataki
 * @Date: 2020/7/20
 * Time: 12:04
 * description: 167. Two Sum II - Input array is sorted
 *
 *Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
 *
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
 *
 * Note:
 *
 * Your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution and you may not use the same element twice.
 * Example:
 *
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Question167 {

    public static void main(String[] args) {
        Question167 q = new Question167() ;
        int[] reInts = null ;
        reInts = q.twoSum(new int[]{0,4,3,0},0);
        reInts = q.twoSum(new int[]{2,7,11,15},0);

        System.out.println(reInts[0]);

        System.out.println(reInts[1]);

    }

    public int[] twoSum(int[] numbers, int target) {


        HashMap<Integer,Integer> map = new HashMap<>(numbers.length) ;

        for(int i=0 ; i<numbers.length ; i++){
            if( map.get(target-numbers[i])== null  ){
                map.put(numbers[i] , i );
            }else{
                /**
                 * 直接返回一个new对象：
                 * 1.可以结束for循环，尽可能的到达最优复杂度
                 * 2.在使用的时候开辟空间，符合java语言
                 */
                return new int[]{ map.get(target- numbers[i])+1 , i+1 } ;
            }
        }

        return new int[]{};

    }
}
