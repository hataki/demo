package com.leecode.day2;

import com.leecode.difficult.Question41;

/**
 * @Author: hataki
 * @Date: 2020/6/28
 * Time: 10:17
 * description:
 *
 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 *
 * Example: 
 *
 * Input: s = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: the subarray [4,3] has the minimal length under the problem constraint.
 * Follow up:
 * If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n). 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Question209 {

    public static void main(String[] args) {

        Question209 q = new Question209();

        System.out.println("result is : " + q.minSubArrayLen(9,new int[]{2,3,1,2,4,3}));

        System.out.println("result is : " + q.minSubArrayLen(15,new int[]{5,1,3,5,10,7,4,9,2,8}));


    }


    /**
     * the return array`s minimal length should be in [1,nums.length] ;
     * note : there should be subarray . elements is in order .
     * why set res = Integer.MAX_VALUE ?
     * for reason why
     * 防止因为target太多导致sum重复循环找不到最小数组时
     *
     */
    public int minSubArrayLen(int s, int[] nums) {
        int len = nums.length ;
        int res = Integer.MAX_VALUE;
        if(len == 0){
            return 0 ;
        }
        int begin = 0 , end = 0;
        int sum = 0 ;
        while(end < len ){
           sum += nums[end] ;
           while( sum >= s && begin >= 0 ){
               res = Math.min(res, end - begin + 1);
               sum -= nums[begin];
               begin++ ;
           }

           end++ ;
        }
        res = (res == Integer.MAX_VALUE  ? 0 : res) ;
        return res ;
    }
}
