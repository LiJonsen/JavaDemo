package JDBC_test.dao;

import JDBC_test.bean.Customer;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * 定义针对Customers表操作的接口
 */
public interface CustomerDAO {
    /**
     * @Description 根据cust的id修改表记录
     * @param conn
     * @param cust
     * @return
     */
    boolean modifyById(Connection conn, Customer cust);
    /**
     * @Description 根据id删除表记录
     * @param conn
     * @param id
     * @return
     */
    boolean deleteById(Connection conn,int id);
    /**
     * @Description 插入一条记录
     * @param conn
     * @param cust
     * @return
     */
    boolean insert(Connection conn,Customer cust);

    /**
     * @Description 根据id查询一条记录
     * @param conn
     * @param id
     * @return
     */
    Customer getCustomerById(Connection conn,int id);

    /**
     * @Description 查询customers表所有记录
     * @param conn
     * @return
     */
    List<Customer> getAll(Connection conn);

    /**
     * @Description 查询customers表的记录总数量
     * @param conn
     * @return
     */
    Number getCount(Connection conn);

    /**
     * @Description 查询customers表中birth最大的日期
     * @param conn
     * @return
     */
    Date getMaxDate(Connection conn);
}
