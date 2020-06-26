package com.leecode.day1;

import java.math.BigInteger;

/**
 * @Author: hataki
 * @Date: 2020/6/23
 * Time: 9:24
 * description:
 * Given two binary strings, return their sum (also a binary string).
 *
 * The input strings are both non-empty and contains only characters 1 or 0.
 *
 * Example 1:
 *
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 *
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 *  
 *
 * Constraints:
 *
 * Each string consists only of '0' or '1' characters.
 * 1 <= a.length, b.length <= 10^4
 * Each string is either "0" or doesn't contain any leading zero.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-binary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 怎么解决进位问题？
 */
public class Question67 {


    public static void main(String[] args) {
        Question67 q = new Question67() ;
        String re = q.addBinary(new String("10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101"), new String("110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011")) ;
//        String re = q.addBinary(new String("1010"), new String("1011")) ;
        System.out.println("output : " );
        System.out.println(re);

    }


    public String addBinary(String a, String b) {

            BigInteger ia = new BigInteger(a, 2);
            BigInteger ib = new BigInteger(b, 2);
            BigInteger xx = ia.add(ib);
            return new BigInteger(xx.toString(),10).toString(2);
    }




    public String addBinary2(String a, String b) {
        char[] ca = a.toCharArray();
        char[] cb = b.toCharArray();
        char[] cc = new char[a.length() + b.length()];
        cc = cb ;
        char temp = '0' ;
        int minLength = ca.length <= cb.length ? ca.length : cb.length ;
        for(int i=minLength-1 ; i >= 0 ; i--){
            /**
             * 先判断循环进位
             */
            if(ca[i] == 1 ){
                if(temp == '1'){
                    ca[i] = ca[i] == temp ? '0' : '1' ;
                    temp = '0' ;
                }
            }else{
                if(temp == '1'){
                    ca[i] = ca[i] == temp ? '0' : '1' ;
                }
            }
            cc[i] = ca[i] ==  cb[i] ? '0' : '1' ;
        }

        return String.valueOf(cc) ;
    }

}
