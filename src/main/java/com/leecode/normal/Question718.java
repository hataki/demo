package com.leecode.normal;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

/**
 * @Author: hataki
 * @Date: 2020/7/1
 * Time: 9:40
 * description: Maximum Length of Repeated Subarray
 *
 * Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
 *
 * Example 1:
 *
 * Input:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * Output: 3
 * Explanation:
 * The repeated subarray with maximum length is [3, 2, 1].
 *  
 *
 * Note:
 *
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Question718 {

    public static void main(String[] args) {
//        Integer x = 100 ;
//        Integer y = 100 ;
//        Integer n = 200 ;
//        Integer m = 200 ;
//
//        System.out.println(x == y );
//        System.out.println(n == m );
//        System.out.println(n.getClass().hashCode() );
//        System.out.println(m.getClass().hashCode() );
        Question718 q = new Question718();
        int[] a = {1,2,3,2,1} ;
        int[] b = {3,2,1,4,7} ;
        System.out.println(q.findLength(a,b));

    }


    /**
     * cause 0 <= A[i], B[i] < 100 , A[i], B[i] share same memory ;
     *
     */
    public int findLength(int[] A, int[] B) {

        int len = A.length < B.length ? A.length : B.length ;
        Integer x , y ;
        int res = 0 ;
        int temp = 0 ;
        for(int i = len ;i > 0 ; i--){
            x = A[i];
            y = B[i];
            if(x.getClass().hashCode() == y.getClass().hashCode()){
                temp++ ;
            }else{
                res = res > temp ? res : temp ;
                temp = 0 ;
            }
        }
        return res ;
    }
}
