package com.leecode.day1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author: hataki
 * @Date: 2020/6/22
 * Time: 13:40
 * description:
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 *  示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Question1 {



    public static void main(String[] args) {
        Question1 q = new Question1();

        long start = System.currentTimeMillis();
        int[] reInts = null ;
        reInts = q.twoSum(new int[]{0,4,3,0},0);
        reInts = q.twoSum(new int[]{2,7,11,15},0);

        System.out.println(reInts[0]);

        System.out.println(reInts[1]);
        long stop = System.currentTimeMillis();
        System.out.println("times is " +( stop - start ) );


    }

    /**
     * 利用map数组构造映射，遍历nums[i]时，看target-nums[i]是否存在hash表中即可
     * 时间复杂度O（n），空间复杂度O（n）
     * 4 ms	39.9 MB
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer,Integer> map = new HashMap<>() ;
        for(int i=nums.length-1 ; i>=0 ; i--){
            if( map.get(target-nums[i])== null  ){
                map.put(nums[i] , i );
            }else{
                result[0]=i;
                result[1]=map.get(target-nums[i]);
            }
        }

        return result;
    }

    /**
     * 65 ms	39.9 MB
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        int[] result = new int[2];
        for(int i =0 ; i< nums.length ; i++ ){
            if(nums[i] <= target){
                int temp = target - nums[i];
                for(int j = (i+1) ;j<  nums.length ;j++){
                    if(temp == nums[j] && j != i ){
                        result[0]=i;
                        result[1]=j;

                    }
                }

            }

        }
        return result;
    }
}
