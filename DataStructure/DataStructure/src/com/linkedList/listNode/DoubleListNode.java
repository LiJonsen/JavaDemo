package com.linkedList.listNode;

/**
 * @ClassName DoubleListNode
 * @Description 双向链表节点
 * @Author Josen
 * @Date 2020/6/29 20:44
 * @Version 1.0
 **/
public class DoubleListNode {
    public int no;
    public String name;
    public String nickname;
    // 指向下一个节点地址
    public DoubleListNode next;
    // 指向上一个节点地址
    public DoubleListNode pre;

    public DoubleListNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "DoubleListNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
