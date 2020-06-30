package com.linkedList;

import com.linkedList.listNode.DoubleListNode;
import com.linkedList.listNode.ListNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Testing
 * @Description TODO
 * @Author Josen
 * @Date 2020/6/29 12:39
 * @Version 1.0
 **/
public class Testing {
    /**
     * 测试环形单链表
     */
    @Test
    public void testCircleSingleLinkedList(){
        CircleSingleLinkedList csll = new CircleSingleLinkedList();

        csll.addChild(5);
//        csll.showChild();
        csll.countChild(1,2,5);
    }
    /**
     * 测试双链表
     */
    @Test
    public void testDoubleLinkedList(){
        DoubleLinkedList dll = new DoubleLinkedList();
        List<DoubleListNode> list = new ArrayList<>();
        list.add(new DoubleListNode(1, "宋江", "及时雨"));
        list.add(new DoubleListNode(2, "卢俊义", "玉麒麟"));
        list.add(new DoubleListNode(3, "吴用", "智多星"));
        list.add(new DoubleListNode(4, "林冲", "豹子头"));
        for (DoubleListNode node : list) {
            dll.addNode(node);
        }
        dll.deleteNode(1);
        dll.updateNode(new DoubleListNode(2, "李逵", "biubiubiu"));
        dll.printListNode();

    }
    /**
     * 测试单链表
     */
    @Test
    public void testSingleLinkedList() {
        List<ListNode> list = new ArrayList<>();
        list.add(new ListNode(1, "宋江", "及时雨"));
        list.add(new ListNode(2, "卢俊义", "玉麒麟"));
        list.add(new ListNode(3, "吴用", "智多星"));
        list.add(new ListNode(4, "林冲", "豹子头"));

        SingleLinkedList sll = new SingleLinkedList();
        for (ListNode node : list) {
            sll.add(node);
        }
//        sll.delete(2);
        sll.update(new ListNode(3, "李逵", "哒哒哒"));
//        System.out.println(sll.getLength());

//        sll.showLinkedList(sll.getHead());

//        ListNode reverse_list = sll.reverseList();
//        sll.showLinkedList(reverse_list);

        ListNode lastIndexNode = sll.findLastIndexNode(1);
        System.out.println(lastIndexNode);

        sll.printLastToHead();
    }
}
