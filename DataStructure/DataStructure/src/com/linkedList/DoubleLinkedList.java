package com.linkedList;

import com.linkedList.listNode.DoubleListNode;

/**
 * @ClassName DoubleLinkedList
 * @Description 双向链表实现类
 * @Author Josen
 * @Date 2020/6/29 18:11
 * @Version 1.0
 **/
public class DoubleLinkedList {
    // 头节点
    private DoubleListNode head = new DoubleListNode(0,"","");
    // 尾节点
    private DoubleListNode last = null;

    /**
     * 添加节点（默认添加到最后）
     * @param node
     */
    public void addNode(DoubleListNode node){
        // 创建辅助指针
        DoubleListNode cur = head;
        while (true){
            if(cur.next == null){
                node.pre = cur;
                cur.next = node;
                // 更新尾节点地址
                last = node;
                break;
            }
            cur = cur.next;
        }
    }

    /**
     * 删除指定no节点（从尾部开始查找）
     * 特点：
     * 变更当前删除节点的上一个节点的next地址，
     * 以及下一个节点的pre地址；
     * @param no
     */
    public void deleteNode(int no){
        DoubleListNode cur = last;
        // 根据pre指针判断
        while (cur != null){
            if(cur.no == no){
                // 修改当前前后节点的地址
                if(cur.next != null){
                    cur.next.pre = cur.pre;
                }
                if(cur.pre != null){
                    cur.pre.next = cur.next;
                }
                break;
            }
            cur = cur.pre;
        }
    }

    /**
     * 更新节点
     * 从head开始遍历(no值不可改变)
     * @param node
     */
    public void updateNode(DoubleListNode node){
        DoubleListNode cur = head;
        while ((cur = cur.next) != null){
            if(cur.no == node.no){
                cur.name = node.name;
                cur.nickname = node.nickname;
            }
        }
    }

    /**
     * 打印链表所有节点
     */
    public void printListNode(){
        DoubleListNode cur = head;
        while ((cur=cur.next) != null){
            System.out.println(cur);
        }
    }

    public DoubleListNode getHead() {
        return head;
    }

    public DoubleListNode getLast() {
        return last;
    }
}
