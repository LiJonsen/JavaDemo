package com.linkedList.listNode;

/**
 * @ClassName ListNode
 * @Description 单链表节点
 * @Author Josen
 * @Date 2020/6/29 12:16
 * @Version 1.0
 **/
public class ListNode {
    public int no;
    public String name;
    public String nickname;
    public ListNode next;
    public ListNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
