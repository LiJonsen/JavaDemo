package com.atguigu.threadTest;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程方式三：Callable
 */
public class CallableTest {
    public static void main(String[] args) {
        AddOneThread callable = new AddOneThread();
        FutureTask<Integer> future = new FutureTask<Integer>(callable);
        new Thread(future).start();

        try {
            Integer count = future.get();
            System.out.println("1-100偶数总和："+count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class AddOneThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int count = 0;
        for(int i=0;i<100;i++){
            if(i%2==0){
                count+=i;
                System.out.println(Thread.currentThread().getName()+"："+i);
            }
        }
        return count;
    }
}
