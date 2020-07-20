package com.leecode.day3;

import java.util.List;

/**
 * @Author: hataki
 * @Date: 2020/7/14
 * Time: 11:12
 * description: Triangle
 *
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 *
 * For example, given the following triangle
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 *
 * Note:
 *
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Question120 {


    public static void main(String[] args) {

    }


    public int minimumTotal(List<List<Integer>> triangle) {
        return 0 ;
    }

    public int getMin(List<Integer> list){
        int min = Integer.MIN_VALUE ;
        for(int i =0;i<list.size();i++){
            min = min < list.get(i) ? min : list.get(i);
        }
        return min ;
    }
}
