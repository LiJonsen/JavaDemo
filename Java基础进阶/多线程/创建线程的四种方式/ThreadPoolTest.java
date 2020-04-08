package com.atguigu.threadTest;
import java.util.concurrent.*;

/**
 * 创建线程方式四：线程池
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        // 1.提供指定线程数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        // 2-1.执行指定的线程运行操作（execute：适用于Runnable）
        service.execute(new CreateRunnableTest());
        // 2-2.执行指定的线程运行操作（submit：适用于Callable）
        Object count = null;
        try {
            // 获取Callable返回值
            count = service.submit(new CreateCallableTest()).get();
            System.out.println("1-100奇数总和："+count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 3.关闭连接池
        service.shutdown();
    }
}

class CreateRunnableTest implements Runnable{
    @Override
    public void run() {
        for(int i=0;i<=100;i++){
            if(i%2==0){
                System.out.println("偶数："+i);
            }
        }
    }
}


class CreateCallableTest implements Callable {
    @Override
    public Object call() throws Exception {
        int count=0;
        for(int i=0;i<=100;i++){
            if(i%2!=0){
                System.out.println("奇数："+i);
                count += i;
            }
        }
        return count;
    }
}
