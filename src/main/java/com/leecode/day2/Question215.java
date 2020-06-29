package com.leecode.day2;


import java.util.HashMap;

/**
 * @Author: hataki
 * @Date: 2020/6/28
 * Time: 10:17
 * description:
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Example 1:
 *
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 *
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Question215 {


    /**
     * 反向思路--> 第k个最大值，可以理解为第(nums.length-k)个最小值
     */
    public int findKthLargest(int[] nums, int k) {

        HashMap<Integer,Integer> hm = new HashMap(nums.length);
        int[] xx = nums ;
        int order = nums.length - k + 1  ;
        hm.put( nums[0],order );
        for(int i =1 ;i< nums.length ; i++){
            //以第一个元素为基点
            if(hm.get(order) > nums[i] ){
                hm.put(nums[i] - nums[0],order+i);
            }else if(hm.get(order) > nums[i] ){
                hm.put(nums[0] - nums[i],order-i);
            }else{

            }
        }


        return 0 ;
    }
}
