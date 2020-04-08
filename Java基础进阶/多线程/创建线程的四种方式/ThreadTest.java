package com.atguigu.threadTest;
/**
 * @Description 创建线程Demo
 * @author Josen
 */
public class ThreadTest {
    public static void main(String[] args) {
        CreateThread createThread = new CreateThread();
        // 啟動進程
        createThread.start();
        for(int i =0;i<100;i++){
            if(i%2==0){
                System.out.println(i+"*************");
            }
        }

        CreateThread c2 = new CreateThread();
        c2.start();
    }
}
/**
 * @Description 創建线程
 * 1、 繼承Thread類
 * 2、 重寫run()方法
 * 3、 實例化CreateThread
 * 4、 調用start()開啟進程
 * @author Josen
 */
class CreateThread extends Thread{
    // 進程開始執行後，運行的方法
    @Override
    public void run() {
        for(int i =0;i<100;i++){
            if(i % 2==0){
                System.out.println(Thread.currentThread().getName()+"***"+i);
            }
        }
    }
}
