package com.leecode.day2;

import com.leecode.day1.Question1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: hataki
 * @Date: 2020/6/23
 * Time: 9:24
 * description:
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 *              Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Question139 {

    public static void main(String[] args) {

        Question139 q = new Question139();
        List<String> wordDict = new ArrayList<>() ;
        wordDict.add("leet");
        wordDict.add("code");
        String s = "leetcode";
        boolean result = q.wordBreak(s,wordDict);
        System.out.println("result is :" + result);

        wordDict.clear();
        wordDict.add("car");
        wordDict.add("ca");
        wordDict.add("rs");
        s = "cars";
        result = q.wordBreak(s,wordDict);
        System.out.println("result is :" + result);

        wordDict.clear();
        wordDict.add("a");
        wordDict.add("b");
        wordDict.add("bbb");
        wordDict.add("bbbb");
        s = "bb";
        result = q.wordBreak(s,wordDict);
        System.out.println("result is :" + result);

        wordDict.clear();
        wordDict.add("apple");
        wordDict.add("pen");
        s = "applepenapple";
        result = q.wordBreak(s,wordDict);
        System.out.println("result is :" + result);

        wordDict.clear();
        wordDict.add("cat");
        wordDict.add("sand");
        wordDict.add("dog");
        s = "catsandog";
        result = q.wordBreak(s,wordDict);
        System.out.println("result is :" + result);

        wordDict.clear();
        wordDict.add("book");
        wordDict.add("kkey");
        wordDict.add("ey");
        s = "bookkeybookkey";
        result = q.wordBreak(s,wordDict);
        System.out.println("result is :" + result);

        wordDict.clear();
        wordDict.add("like");
        wordDict.add("you");
        wordDict.add("ma");
        s = "likeyoumalikemalikeyou";
        result = q.wordBreak(s,wordDict);
        System.out.println("result is :" + result);

    }

    public boolean wordBreak(String s, List<String> wordDict) {
        String temp ;
        String[] temps ;

        for(int i =0 ;i < wordDict.size(); i++ ){

            if( s.equals(wordDict.get(i))){
                return true ;
            }
            temps = s.split(wordDict.get(i));
            if(temps != null && temps.length > 0) {
                List<String> wordDictNew = wordDict ;
                if(wordDictNew.size() > 1){
                    wordDictNew.remove(i) ;
                }
                if(temps.length == 1 ){
                    if(wordDictNew.get(i).contains(temps[0])){
                        return true ;
                    }
                    return this.wordBreak(temps[0],wordDictNew) ;
                }else if(temps.length == 2 ){

                    temp = temps[1] ;
                    return this.wordBreak(temp,wordDictNew) ;
                }else if(temps.length > 2 ){
                    for(int x=0 ;x<temps.length;x++){
                        if(wordDict.get(i).equals(temps[x])){
                            temp = temps[x];
                            return this.wordBreak(temp,wordDictNew) ;
                        }
                    }
                }

            }else{
                return true ;
            }

        }
        return false ;
    }


    public boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> set = new HashSet<String>();
        for(String word : wordDict)set.add(word);

        boolean[] dp = new boolean[s.length() + 1]; dp[0] = true;
        for(int i = 1; i <= s.length(); ++i){
            for(int j = 1; j <= i; ++j){
                if(set.contains(s.substring(i - j, i)) && dp[i - j]){
                    dp[i] = true;
                }
            }
        }
        return dp[s.length()];
    }
}
