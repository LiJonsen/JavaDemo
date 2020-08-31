package com.josen.beans;

import org.springframework.stereotype.Component;

/**
 * @ClassName Account
 * @Description
 * @Author Josen
 * @Create 2020/8/27 12:23
 */
@Component
public class Account {
    private String username;
    private Double balance;

    public Account() {
    }

    public Account(String username, Double balance) {
        this.username = username;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", balance=" + balance +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
