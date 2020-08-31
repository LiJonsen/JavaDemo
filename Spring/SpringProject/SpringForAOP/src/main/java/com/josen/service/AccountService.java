package com.josen.service;

import com.josen.beans.Account;
import com.josen.dao.AccountDao;
import com.josen.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * @ClassName AccountService
 * @Description 账户业务逻辑
 * @Author Josen
 * @Create 2020/8/27 14:36
 */
@Service
public class AccountService {
    @Autowired
    private AccountDao accountDao;
    /**
     * 余额转账
     * @param from 转出账户
     * @param to 收账账户
     * @param money 转账金额
     */
    public void transferBalance(Account from, Account to,Double money) throws SQLException {

        double balance = from.getBalance();
        from.setBalance(balance - money);
        // 1. 减少转出账户余额
        accountDao.updateBalance(from);
        // 测试回滚
//        int i = 1/0;
//        System.out.println(i);
        // 2. 增加收账账户余额
        double to_balance = to.getBalance();
        to.setBalance(to_balance+money);
        accountDao.updateBalance(to);
    }

    public Account getAccountByUsername(String name) throws SQLException {
        return accountDao.queryAccountByName(name);
    }
}
