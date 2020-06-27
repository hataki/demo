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
     * so i try to use double pointers at first .×
     */


    /**
     * copy
     * 执行结果： 通过
     * 显示详情 执行用时： 1 ms
     * 在所有 Java 提交中击败了 87.52% 的用户
     * 内存消耗： 37.9 MB ,
     * 在所有 Java 提交中击败了 8.33% 的用户
     */

    public int firstMissingPositive(int[] nums) {
        int len = nums.length ;
        for(int i=0 ;i < len ; i++){
            while(nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1 ] != nums[i]){
                /**
                 * match condition and not suit in right set
                 */
                swap(nums,nums[i]-1 ,i );
            }
        }

        for(int i=0;i<len;i++){
            if(nums[i] != i+ 1 ){
                return i+1 ;
            }
        }

        return len + 1 ;

    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }


    public int firstMissingPositive2(int[] nums) {

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
