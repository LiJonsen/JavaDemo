package com.linkedList;
import com.linkedList.listNode.Child;

/**
 * @ClassName CircleSingleLinkedList
 * @Description 创建环形单向链表实现类
 * Joseph（约瑟夫问题）:
 * 设编号为 1，2，… n 的 n 个人围坐一圈，约定编号为 k（1<=k<=n）的人从 1 开始报数，数到
 * m 的那个人出列，它的下一位又从 1 开始报数，数到 m 的那个人又出列，依次类推，直到所有人出列为止，由此
 * 产生一个出队编号的序列;
 * countChild(int startNo,int countNum,int nums)
 * 1. startNo -> k：表示从第k个人开始数数
 * 2. countNum -> m：表示数m下，对应数字的人出列
 * 3. nums -> n：表示有几个人
 * @Author Josen
 * @Date 2020/6/29 18:12
 * @Version 1.0
 **/
public class CircleSingleLinkedList {
//    创建一个 first 节点,当前没有编号
    private Child first;

    /**
     * 添加小孩，根据count值构建一个对应长度的环形链表
     * @param count 小孩数量
     */
    public void addChild(int count){
        // 创建辅助指针
        Child cur = null;

        for(int i=1;i<=count;i++){
            Child child = new Child(i);
            // 设置第一个头节点
            if(i==1){
                first = child;
                first.setNext(child);
                cur = first; // cur指针地址指向第一个节点
                continue;
            }
            // 最后一个节点的next指向first
            child.setNext(first);
            // 添加下一个节点
            cur.setNext(child);
            // 指针指向下一个节点
            cur = child;
        }
    }

    /**
     * 根据用户的输入，计算出小孩出圈的顺序
     * @param startNo
     * 表示从第几个小孩开始数数
     * @param countNum
     * 表示数几下
     * @param nums
     * 表示最初有多少小孩在圈中
     */
    public void countChild(int startNo,int countNum,int nums){
        /**
         *  1.创建要给辅助指针,帮助完成小孩出圈
         *  需求创建一个辅助指针(变量) helper , 事先应该指向环形链表的最后这个节点
         */
        Child helper = first;
        while (true){
            if(helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }

        // 3.小孩报数前，先让 first 和 helper 移动 startNo - 1 次
        for(int i=0;i<startNo-1;i++){
            first = first.getNext();
            helper = helper.getNext();
        }

        /**
         * 4.当小孩报数时，让 first 和 helper 指针同时 的移动 countNum - 1 次, 然后出圈
         * 这里是一个循环操作，直到圈中只有一个节点
         */
        while (true){
            if(helper == first)
                break;
            for(int j=0;j<countNum-1;j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("小孩 %d 出圈\n",first.getNo());
            // 指向下一个节点，当前first出圈
            first = first.getNext();
            // helper指向first修改后的地址
            helper.setNext(first);
        }
        System.out.println("最后一个小孩"+first.getNo()+"出圈");
    }

    /**
     * 遍历所有Child
     */
    public void showChild(){
        Child cur = first;
        while (true){
            System.out.println("Child no="+cur.getNo());
            if(cur.getNext() == first)
                break;
            cur = cur.getNext();
        }
    }
}
