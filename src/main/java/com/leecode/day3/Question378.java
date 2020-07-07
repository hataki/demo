package com.leecode.day3;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: hataki
 * @Date: 2020/7/2
 * Time: 9:47
 * description: 378. Kth Smallest Element in a Sorted Matrix
 *
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
 *
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 * Example:
 *
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]
 * ],
 * k = 8,
 *
 * return 13.
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ n*n.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Question378 {

    public static void main(String[] args) {

    }

    public int kthSmallest(int[][] matrix, int k) {
        //维度
        int n = matrix[0].length;
        Deque<Integer> dq ;
        dq = new LinkedList<>();
        if( k <= n ){
            return matrix[0][k-1] ;
        }else{
            //第一维度数组压栈
           for(int i=0;i<n ;i++){
               dq.addFirst( matrix[0][i]);
           }

        }

        return 0 ;

    }
}
