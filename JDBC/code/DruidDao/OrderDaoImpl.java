package JDBC_test.DruidDao;

import JDBC_test.bean.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderDaoImpl {
    /**
     * 标量查询，返回单个java对象
     * @param conn
     * @return
     */
    Order QueryBean(Connection conn, int id) throws SQLException;

    /**
     * 查询order数据表所有记录
     * @param conn
     * @return
     */
    List<Order> queryAll(Connection conn) throws SQLException;

    /**
     * 添加一条order记录
     * @param conn
     * @param order
     * @return
     */
    int addOrder(Connection conn,Order order) throws SQLException;

    /**
     * 删除一条order记录
     * @param conn
     * @param id
     * @return
     */
    int deleteOrder(Connection conn,int id) throws SQLException;

    /**
     * 根据order.id修改指定数据表记录
     * @param conn
     * @param order
     * @return
     */
    int modifyOrder(Connection conn,Order order) throws SQLException;

    /**
     * 获取order表记录总条数
     * @param conn
     * @return
     */
    long getOrderCount(Connection conn) throws SQLException;
}
