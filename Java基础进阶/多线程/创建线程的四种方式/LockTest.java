package com.atguigu.threadTest;

import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) {
        LockTest.testLock();
    }
    public static void testLock(){
        // 匿名接口实现类和匿名对象
        new Thread(new Runnable() {
            private int total = 100;
            ReentrantLock lock = new ReentrantLock();
            @Override
            public void run() {
                while (total>0){
                    try{
                        lock.lock();
                        Thread.sleep(100);
                        System.out.println("total:"+total);
                        --total;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }

                }
            }
        }).start();
    }
}
