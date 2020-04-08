package com.atguigu.threadTest;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 练习：一个账户，甲和乙各存入3000，分三次存入
 */

class Account{
    private int balance;
    // 存钱
    public void deposit(int money) throws InterruptedException{
        if(money>0){
            this.balance += money;
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+"当前余额："+balance);
        }
    }
}

class Customer implements Runnable{
    private Account acct;
    private ReentrantLock lock = new ReentrantLock();
    public Customer(){
    }
    public Customer(Account acct){
        this.acct = acct;
    }
    @Override
    public void run() {
        for(int i=0;i<30;i++){
            lock.lock();
            try{
                acct.deposit(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            } finally{
                lock.unlock();
            }
        }
    }
}

public class AccountTest {
    public static void main(String[] args) {
        Account acct = new Account();

        Customer customer = new Customer(acct);

        Thread c1 = new Thread(customer);
        Thread c2 = new Thread(customer);

        c1.setName("甲");
        c2.setName("乙");

        c1.start();
        c2.start();
    }
}
