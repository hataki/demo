package com.leecode.day2;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @Author: hataki
 * @Date: 2020/6/30
 * Time: 9:16
 * description:
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 *
 *  
 *
 * 示例 1：
 *
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 * 示例 2：
 *
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 * 提示：
 *
 * 1 <= values <= 10000
 * 最多会对 appendTail、deleteHead 进行 10000 次调用
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class QuestionOffer09 {

    public static void main(String[] args) {

        CQueue c = new CQueue();
        c.appendTail(3);
        c.deleteHead();
        c.deleteHead();
        c.deleteHead();


    }


    /**
     * inner class
     */
    static class CQueue{

        Deque<Integer> sa ;
        Deque<Integer> sb ;



        public CQueue() {
            sa = new LinkedList<>();
            sb = new LinkedList<>();
        }

        public void appendTail(int value) {
            sa.push(value);
        }

        public int deleteHead() {
            /**
             * if sb is empty , pop sa into sb and pop sb
             * else sb pop is the last element .
             */
           if(sb.isEmpty()){
               while(!sa.isEmpty()){
                   sb.push(sa.pop());
               }
           }

           if(!sb.isEmpty()){
               return sb.pop();
           }else{
               return -1 ;
           }


        }

    }


}


/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */