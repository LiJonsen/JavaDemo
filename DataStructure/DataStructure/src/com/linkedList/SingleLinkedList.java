package com.linkedList;

import com.linkedList.listNode.ListNode;

import java.util.Stack;

/**
 * @ClassName SingleLinkedList
 * @Description 单链表实现类
 * 实现对单链表的增删改查功能
 * 附加：
 * 1.单链表的反转【腾讯面试题】
 * 2.查找单链表中的倒数第 k 个节点【新浪面试题】
 * 3.从尾到头打印单链表【百度，要求方式 1：反向遍历 。 方式 2：Stack 栈】
 * @Author Josen
 * @Date 2020/6/29 12:16
 * @Version 1.0
 **/
public class SingleLinkedList {
    // 初始化一个头节点, 头节点不要动, 不存放具体的数据
    private ListNode head = new ListNode(0,"","");
    public SingleLinkedList(){}
    /**
     * add-添加
     * 1. 找到当前链表的最后节点
     * 2. 将最后这个节点的 next 指向 新的节点
     * @param node
     */
    public void add(ListNode node){
        ListNode temp = head;
        // 遍历找到最后一个节点,添加链表节点
        while (true){
            if(temp.next == null){
                temp.next = node;
                break;
            }
            temp = temp.next;
        }
    }

    /**
     * delete-删除
     * 1. head 不能动，因此我们需要一个 temp 辅助节点找到待删除节点的前一个节点
     * 2. 说明我们在比较时，是 temp.next.no 和 需要删除的节点的 no 比较
     */
    public boolean delete(int no){
        ListNode temp = head;
        if(temp.next==null)
            return false;

        boolean flag = false;
        while (true){
            // temp:为待删除节点的上一个节点
            // 判断当前下一个节点no值
            if(temp.next.no == no){
                flag = true;
                break;
            }
            temp=temp.next;
        }
        // 修改地址值
        if(flag)
            temp.next = temp.next.next;
        else
            System.out.println("节点不存在");
        return true;
    }

    /**
     * update-修改
     * 修改节点的信息, 根据 no 编号来修改，即 no 编号不能改
     */
    public void update(ListNode node){
        ListNode temp = head;
        while ((temp=temp.next) != null){
            if(temp.no == node.no){
                temp.name = node.name;
                temp.nickname = node.nickname;
                break;
            }
        }
    }

    /**
     * showLinkedList
     * 显示链表所有节点信息
     */
    public void showLinkedList(ListNode list){
        ListNode pre = list;
        System.out.println("*****遍历链表所有节点*****");
        while ( (pre=pre.next) !=null ){
            System.out.println(pre);
        }
    }

    /**
     * getLength
     * 获取有效节点个数
     * @return
     */
    public int getLength(){
        int len=0;
        ListNode pre = head;
        while((pre=pre.next) != null){
            len++;
        }
        return len;
    }
    /**
     * reverseList
     * 单链表的反转
     * 实现思路(利用栈的先入后出)：将正序的单链表压入栈中，然后取出
     */
    public ListNode reverseList(){
        // 如果只有一个节点，直接返回
        if(head.next == null || head.next.next ==null)
            return head;
        // 将链表压入Stack中
        Stack<ListNode> stack = new Stack<>();
        ListNode pre = head;
        while((pre=pre.next) != null){
            stack.push(pre);
        }
        // 在Stack中取出节点，实现链表反转
        int size = stack.size();
        ListNode reverseList = new ListNode(0,"","");
        ListNode cur = reverseList;

        ListNode temp;
        while((--size)>=0){
            temp = stack.pop();
            cur.next = new ListNode(temp.no,temp.name,temp.nickname);
            cur = cur.next;
        }
        return reverseList;
    }
    /**
     * findLastIndexNode
     * 查找单链表中的倒数第 k 个节点
     */
    public ListNode findLastIndexNode(int k){
        if(head.next == null){
            System.out.println("链表为空");
            return null;
        }
        // 如果只有一个节点，则返回
        if(head.next != null && head.next.next ==null)
            return head.next;

        // 反转链表
        ListNode reverse_list = reverseList();

        // 获取第k个节点
        int cur = 0;
        ListNode pre = reverse_list;
        while ((cur++)<k && (pre=pre.next) != null){}
        return pre;
    }

    /**
     * printLastToHead
     * 从尾到头打印单链表
     */
    public void printLastToHead(){
        ListNode reverse_list = reverseList();
        System.out.println("*********从尾到头打印单链表**************");
        showLinkedList(reverse_list);
    }

    /**
     * 返回头节点
     * @return
     */
    public ListNode getHead() {
        return head;
    }
}
