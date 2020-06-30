package com.linkedList.listNode;

/**
 * @ClassName Child
 * @Description 环形单链表节点
 * @Author Josen
 * @Date 2020/6/29 21:21
 * @Version 1.0
 **/
public class Child {
    private int no;
    private Child next;
    public Child(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Child getNext() {
        return next;
    }

    public void setNext(Child next) {
        this.next = next;
    }
}
