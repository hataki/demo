package com.leecode.AlgorithmsNotes;

/**
 * @Author: hataki
 * @Date: 2020/7/1
 * Time: 10:00
 * description:
 */
public class AlgorithmUtil {


    public static boolean isEqual (int[] arrA , int[] arrB) {

        if( (arrA == null && arrB != null) || (arrA != null && arrB == null)){
            return false ;
        }
        if( arrA == null && arrB == null ){
            return true ;
        }
        if( arrA.length != arrB.length){
            return false ;
        }
        for(int i = 0 ; i<arrA.length ; i ++ ){
            if(arrA[i] != arrB[i]){
                return false ;
            }
        }
        return true  ;
    }
}
