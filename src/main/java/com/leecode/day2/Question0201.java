package com.leecode.day2;

import java.util.HashMap;

public class Question0201 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);

        head.val = 1;

        while(head.next != null ){
            System.out.println(head.val);
            head.next = head.next.next ;
        }

//        Question0201 q = new Question0201();
//        q.removeDuplicateNodes(head);

    }

    /**
     * 不使用临时缓存区
     * @param head
     * @return
     */
    public ListNode removeDuplicateNodes(ListNode head) {


        ListNode highHead = head ;

        if(head == null ){
           return head ;
        }

        while(highHead != null){
            ListNode lowHead = highHead ;
            while (lowHead.next != null ){
                if(lowHead.next.val == highHead.val){
                    lowHead.next = lowHead.next.next ;
                }else{
                    lowHead = lowHead.next ;
                }
            }
            highHead = highHead.next ;
        }
        return head ;
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
