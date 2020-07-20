package com.leecode.day3;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: hataki
 * @Date: 2020/7/9
 * Time: 9:42
 * description: 17.13. Re-Space LCCI
 *
 * Oh, no! You have accidentally removed all spaces, punctuation, and capitalization in a lengthy document. A sentence like "I reset the computer. It still didn't boot!" became "iresetthecomputeritstilldidntboot''. You'll deal with the punctuation and capi­talization later; right now you need to re-insert the spaces. Most of the words are in a dictionary but a few are not. Given a dictionary (a list of strings) and the document (a string), design an algorithm to unconcatenate the document in a way that minimizes the number of unrecognized characters. Return the number of unrecognized characters.
 *
 * Note: This problem is slightly different from the original one in the book.
 *
 *  
 *
 * Example:
 *
 * Input:
 * dictionary = ["looked","just","like","her","brother"]
 * sentence = "jesslookedjustliketimherbrother"
 * Output:  7
 * Explanation:  After unconcatenating, we got "jess looked just like tim her brother", which containing 7 unrecognized characters.
 * Note:
 *
 * 0 <= len(sentence) <= 1000
 * The total number of characters in dictionary is less than or equal to 150000.
 * There are only lowercase letters in dictionary and sentence.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/re-space-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Question17_13 {

    public static void main(String[] args) {
        Question17_13 q = new Question17_13() ;
        String[] dictionary = {"looked","just","like","her","brother"};
        String sentence = "jesslookedjustliketimherbrother" ;
        System.out.println(q.respace(dictionary,sentence));


        String[] dictionary2 = {"potimzz"};
        sentence = "potimzzpotimzz";
        System.out.println(q.respace(dictionary2,sentence));





    }

    public int respace(String[] dictionary, String sentence) {

        if(dictionary == null || dictionary.length == 0 ){
            return sentence.length();
        }
        StringBuilder sb = new StringBuilder(sentence);
        int len = sentence.length();
        for(int i =0;i<dictionary.length ;i++){
                sb.delete(sb.indexOf(dictionary[i]),sb.indexOf(dictionary[i])+dictionary[i].length());
                while( sb.indexOf(dictionary[i]) != -1 ){

                }
        }
        // 怎么去除字典中重复的字符串？？？/


        return sb.toString().length() ;

    }
}
