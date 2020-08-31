package com.josen.dao;

import com.josen.beans.Account;
import com.josen.utils.TransactionManager;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName AccountDao
 * @Description
 * @Author Josen
 * @Create 2020/8/27 14:15
 */
@Repository
public class AccountDao {
    @Autowired
    private TransactionManager txManager;
    @Autowired
    private QueryRunner runner;
    public Account queryAccountByName(String name) throws SQLException {
        Connection conn = txManager.getCurrentConn();
        String sql = "select username,balance from account where username=?";
        return runner.query(conn, sql, new BeanHandler<>(Account.class),name);
    }

    /**
     * 更新账户余额
     * @param account
     */
    public void updateBalance(Account account) throws SQLException {
        Connection conn = txManager.getCurrentConn();
        String sql = "update account set balance=? where username=?";
        runner.update(conn,sql,account.getBalance(),account.getUsername());
    }
}
