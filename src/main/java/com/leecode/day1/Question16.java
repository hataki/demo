package com.leecode.day1;

import java.util.Arrays;
/**
 * @Author: hataki
 * @Date: 2020/6/23
 * Time: 9:24
 * description:
 */
public class Question16 {

    public static void main(String[] args) {
        Question16 q = new Question16() ;
        int x = q.threeSumClosest(new int[]{-1,2,1,-4} ,1 );
        System.out.println(x);
    }


    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for(int i=0;i<nums.length;i++) {
            int start = i+1, end = nums.length - 1;
            while(start < end) {
                int sum = nums[start] + nums[end] + nums[i];
                if(Math.abs(target - sum) < Math.abs(target - ans))
                    ans = sum;
                if(sum > target)
                    end--;
                else if(sum < target)
                    start++;
                else
                    return ans;
            }
        }
        return ans;
    }
}
