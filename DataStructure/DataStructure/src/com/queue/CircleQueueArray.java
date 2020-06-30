package com.queue;
/**
 * @ClassName CircleQueueArray
 * @Description 数组模拟环形队列
 * 实现队列空间的复用性(%取模maxSize)
 * 1.1 front 和 rear 默认值=0
 * 1.2 判断队列已满：（rear+1）% maxSize == front
 * 1.3 获取队列中有效的数据个数：(rear + maxSize - front) % maxSize
 * 1.4 添加队列：(rear + 1) % maxSize
 * 1.5 获取队列：(front + 1) % maxSize
 * @Author Josen
 * @Date 2020/6/28 15:51
 * @Version 1.0
 **/
public class CircleQueueArray {
    // 队列数组
    private int[] list;
    // 队列头
    private int front;
    // 队列尾
    private int rear;
    // 表示队列最大容量
    private int maxSize;

    public CircleQueueArray(int maxSize){
        this.maxSize = maxSize;
        // 初始化队列数组长度
        list = new int[maxSize];
    }
    /**
     * 添加队列
     * @param val
     */
    public void addQueue(int val){
        if(queueFull()){
            System.out.println("队列已满");
            return;
        }
        list[rear] = val;
        rear = (rear+1) % maxSize;
    }

    /**
     * 获取队列
     * @return
     */
    public int getQueue(){
        if(queueIsEmpty()){
            throw new RuntimeException("当前队列为空");
        }
        int res = list[front];
        front = (front+1) % maxSize;
        return res;
    }
    // 队列是否为空
    public boolean queueIsEmpty(){
        return front==rear;
    }
    // 队列是否已满
    public boolean queueFull(){
        return (rear + 1) % maxSize == front;
    }
    // 遍历队列中有效数据的所有信息
    public void showQueue(){
        if(queueIsEmpty()){
            System.out.println("当前队列为空");
        }
        int len = front+size();
        for(int i=front;i<len;i++){
            int index = i%maxSize;
            int val = list[index];
            System.out.printf("list[%d]=%d\n", index, val);
        }
    }
    // 返回队列中有效的数据个数
    private int size(){
        return (rear + maxSize - front) % maxSize;
    }
    //  显示队列的头数据
    public int headQueue(){
        if(queueIsEmpty()){
            throw new RuntimeException("队列为空");
        }
        return list[front];
    }
}
