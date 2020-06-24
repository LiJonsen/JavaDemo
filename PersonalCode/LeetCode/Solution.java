package com.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Solution
 * @Description 两数相加
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * @Author Josen
 * @Date 2020/6/24 11:33
 * @Version 1.0
 **/
public class Solution {
    public static void main(String[] args) {
        Solution  solution = new Solution();
        // 创建数字链表结构 2 -> 4 -> 3 对应数字：243
        List<Integer> num1 = new ArrayList<>(Arrays.asList(2,4,3));
        // 创建数字链表结构 5 -> 6 -> 4 对应数字：564
        List<Integer> num2 = new ArrayList<>(Arrays.asList(5,6,4));
        solution.calculationNums(solution,num1,num2);
    }

    /**
     * 开始计算整合num1和num2集合中的数相加
     * @param solution
     * @param num1
     * @param num2
     */
    public void calculationNums(Solution solution,List<Integer> num1,List<Integer> num2){
        // 反转数字，先从个位计算，调用addTwoNumbers()时，需要满十进一
        ListNode l2 = new ListNode(num2.get(2));
        // 将集合转换成链表结构
        ListNode listNode = solution.convertListToListNode(num1);
        ListNode listNode2 = solution.convertListToListNode(num2);
//        System.out.println("listNode:"+listNode);
        StringBuilder s1 = solution.showLinkedListRes(listNode);
        StringBuilder s2 = solution.showLinkedListRes(listNode2);
        ListNode res = solution.addTwoNumbers(listNode, listNode2);
        StringBuilder s3 = solution.showLinkedListRes(res);

        solution.printResult(s1,s2,s3);

    }

    /**
     * 打印链表结果-正序+反序
     * @param s1
     * @param s2
     * @param s3
     */
    public void printResult(StringBuilder s1,StringBuilder s2,StringBuilder s3){


        System.out.println("**************反序打印链表两数相加结果******************");
        System.out.print(s1+"+");
        System.out.print(s2+"=");
        System.out.print(s3);
        System.out.println();

        System.out.println("**************正序打印链表两数相加结果******************");
        System.out.print(s1.reverse()+"+");
        System.out.print(s2.reverse()+"=");
        System.out.print(s3.reverse());

    }
    /**
     * 将集合转换成链表结构
     * @param list
     * @return
     */
    public ListNode convertListToListNode(List<Integer> list){
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int len = list.size();
        while ((len--)>0){
            cur.next = new ListNode(list.get(len));
            cur = cur.next;
        }
        return pre.next;
    }

    /**
     * showLinkedListRes
     * 遍历打印链表结果-(反序)
     * @param pre
     * @return 链表结果字符串
     */
    public StringBuilder showLinkedListRes(ListNode pre){
        StringBuilder str = new StringBuilder(String.valueOf(pre.val));
        while ( (pre = pre.next) != null){
            str.append(pre.val);
        }
        return str;
    }

    /**
     * 两数相加算法实现
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1,ListNode l2){
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while(l1 != null || l2 != null){
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;

            int sum = x + y + carry;
            carry = sum / 10;
//            System.out.println("x:"+x+"-y:"+y+"-sum:"+sum+"-carry:"+carry);
            sum = sum % 10;
            cur.next = new ListNode(sum);
            cur = cur.next;
            if(l1 != null)
                l1 = l1.next;
            if(l2 != null)
                l2 = l2.next;
        }
        if(carry == 1){
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }
}

/**
 * 链表节点类
 */
class ListNode{
    int val;
    ListNode next;
    ListNode(int x){ val = x; }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }
}
