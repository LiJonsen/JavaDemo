package com.queue;

/**
 * @ClassName ArrayQueue
 * @Description 数组队列
 * 特点：遵循先入先出的原则
 * 问题：队列空间只能使用一次，当rear==maxSize-1时，不能再添加数据；
 * 解决思路（数组模拟环形队列）-> CircleQueueArray
 * @Author Josen
 * @Date 2020/6/28 12:04
 * @Version 1.0
 **/
public class ArrayQueue {
    // 队列数组
    private int[] list;
    // 队列头
    private int front;
    // 队列尾
    private int rear;
    // 表示队列最大容量
    private int maxSize;

    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        // 初始化队列数组长度
        list = new int[maxSize];
        // 指向队列头部，分析出 front 是指向队列头的前一个位置.
        front = -1;
        // 指向队列尾，指向队列尾的数据(即就是队列最后一个数据)
        rear = -1;
    }

    /**
     * 添加队列
     * @param val
     */
    public void addQueue(int val){
        if(queueFull()){
            throw new RuntimeException("队列已满");
        }
        rear++; // rear后移
        list[rear] = val;
    }

    /**
     * 获取队列
     * @return
     */
    public int getQueue(){
        if(queueIsEmpty()){
            throw new RuntimeException("当前队列为空");
        }
        front++; //后移front
        return list[front];
    }
    // 队列是否为空
    public boolean queueIsEmpty(){
        return front==rear;
    }
    // 队列是否已满
    public boolean queueFull(){
        return rear == maxSize-1;
    }
    // 显示队列信息
    public void showQueue(){
        if(queueIsEmpty()){
            System.out.println("当前队列为空");
        }
        for(int i=0;i<list.length;i++){
            System.out.printf("list[%d]=%d\n", i, list[i]);
        }
    }
    //  显示队列的头数据
    public int headQueue(){
        if(queueIsEmpty()){
            throw new RuntimeException("队列为空");
        }
        return list[front+1];
    }
}
