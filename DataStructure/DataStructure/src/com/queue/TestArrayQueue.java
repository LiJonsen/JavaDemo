package com.queue;

import org.junit.Test;

import java.util.Scanner;

/**
 * @ClassName TestArrayQueue
 * @Description TODO
 * @Author Josen
 * @Date 2020/6/28 14:40
 * @Version 1.0
 **/
public class TestArrayQueue {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        // 普通数组队列
//        ArrayQueue queue = new ArrayQueue(5);
        // 数组模拟环形队列
        CircleQueueArray queue = new CircleQueueArray(3);
        boolean loop = true;
        char key;
        while (loop){
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符

            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'e':
                    loop = false;
                    break;
                case 'a':
                    System.out.println("输入一个要加入队列的数值：");
                    int i = scanner.nextInt();
                    queue.addQueue(i);
                    break;
                case 'g':
                    System.out.println("获取队列：");
                    int res = queue.getQueue();
                    System.out.println(res);
                    break;
                case 'h':
                    int head = queue.headQueue();
                    System.out.println("获取队列头数据：");
                    System.out.println(head);
                    break;
                default:
                    break;
            }
        }

        System.out.println("退出程序...");

    }
}
