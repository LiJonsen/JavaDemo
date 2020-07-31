package com.jdbc_template.beans;

/**
 * @ClassName Account
 * @Description Details
 * @Author Josen
 * @Create 2020/7/28 12:29
 */
public class Account {
    private String username;
    private Integer balance;

    public Account() {
    }

    public Account(String username, Integer balance) {
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

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
