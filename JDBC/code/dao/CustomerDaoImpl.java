package JDBC_test.dao;

import JDBC_test.bean.Customer;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * @Description CustomerDAO接口实现类
 */
public class CustomerDaoImpl extends BaseDao<Customer> implements CustomerDAO{

    @Override
    public boolean modifyById(Connection conn, Customer cust) {
        String sql = "update customers set name=?,email=?,birth=? where id=?";
        return updateSqlTable(conn,sql,cust.getName(),cust.getEmail(),cust.getBirth(),cust.getId());
    }

    @Override
    public boolean deleteById(Connection conn, int id) {
        String sql = "delete from customers where id=?";
        return updateSqlTable(conn,sql,id);
    }

    @Override
    public boolean insert(Connection conn, Customer cust) {
        String sql = "insert into customers(name,email,birth) values(?,?,?)";
        return updateSqlTable(conn,sql,cust.getName(),cust.getEmail(),cust.getBirth());
    }

    @Override
    public Customer getCustomerById(Connection conn, int id) {
        String sql = "select name,email,birth from customers where id = ?";
        List<Customer> result = getResult(conn, Customer.class, sql, id);
        return result.size()>0?result.get(0):null;
    }

    @Override
    public List<Customer> getAll(Connection conn) {
        String sql = "select name,email,birth from customers";
        List<Customer> result = getResult(conn, Customer.class, sql);
        return result;
    }

    @Override
    public Long getCount(Connection conn) {
        String sql = "select count(*) from customers";
        return getValue(conn, sql);
    }

    @Override
    public Date getMaxDate(Connection conn) {
        String sql = "select max(birth) from customers";
        return getValue(conn, sql);
    }
}
