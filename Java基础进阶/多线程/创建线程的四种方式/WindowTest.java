package com.atguigu.threadTest;
/**
 * 練習：創建三個線程，售賣100張票
 * @Description 練習兩種線程創建方式
 * @author Josen
 */
public class WindowTest {
    public static void main(String[] args) {
        WindowTest test = new WindowTest();
//        test.testRunnable();
        test.testThread();
    }
    // 測試Runnable方式
    public void testRunnable(){
        RunnbaleThread runnable = new RunnbaleThread();
        Thread r_t1 = new Thread(runnable);
        Thread r_t2 = new Thread(runnable);
        Thread r_t3 = new Thread(runnable);
        r_t1.setName("Runnable窗口1");
        r_t2.setName("Runnable窗口2");
        r_t3.setName("Runnable窗口3");
        r_t1.start();
        r_t2.start();
        r_t3.start();
    }
    // 測試繼承Thread方式
    public void testThread(){
        AddThread t1 = new AddThread();
        AddThread t2 = new AddThread();
        AddThread t3 = new AddThread();

        t1.setName("Thread窗口1");
        t2.setName("Thread窗口2");
        t3.setName("Thread窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}

/**
 * 實現Runnable接口方式
 */
class RunnbaleThread implements Runnable{
    private int total=100; //票总数
    private Object lock = new Object(); //锁
    @Override
    public void run() {
        // synchronized：进程同步代码块，解决线程安全问题，处理出现错票、重票；
        synchronized(lock){
            while (total>0){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"售出票號："+total);
                --total;
            }
        }

    }
}
/**
 * 繼承Thread方式
 */
 class AddThread extends Thread{
     private static int total = 100;
    @Override
    public void run() {
       show();
    }

    // 同步方法：解决线程安全问题
    // 此时synchronized的同步监视器默认=this (t1,t2,t3)
    // 需要将该方法设为static保证唯一性
    private static synchronized void show(){
        while (total>0){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"售出票號："+total);
            --total;
        }
    }
}