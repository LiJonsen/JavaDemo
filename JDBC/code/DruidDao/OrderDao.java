package JDBC_test.DruidDao;
import JDBC_test.bean.Order;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * StudentDao接口实现类
 * 提供order数据表的操作功能
 */
public class OrderDao extends BaseDao<Order> implements OrderDaoImpl {
    @Override
    public Order QueryBean(Connection conn, int id) throws SQLException {
        String sql = "select order_id orderID,order_name orderName,order_date orderDate from `order` where order_id=?";
        return scalarQuery(conn, sql, id);
    }

    @Override
    public List<Order> queryAll(Connection conn) throws SQLException {
        String sql = "select order_id orderID,order_name orderName,order_date orderDate from `order`";
        return query(conn, sql);
    }

    @Override
    public int addOrder(Connection conn, Order order) throws SQLException {
        String sql = "insert into `order`(order_name,order_date) values(?,?)";
        return update(conn, sql, order.getOrderName(), order.getOrderDate());
    }

    @Override
    public int deleteOrder(Connection conn, int id) throws SQLException {
        String sql = "delete from `order` where order_id=?";
        return update(conn, sql, id);
    }

    @Override
    public int modifyOrder(Connection conn, Order order) throws SQLException {
        String sql = "update `order` set order_name=?,order_date=? where order_id=?";
        return update(conn, sql, order.getOrderName(), order.getOrderDate(),order.getOrderID());
    }

    @Override
    public long getOrderCount(Connection conn) throws SQLException {
        String sql = "select count(*) from `order`";
        return queryValue(conn,sql);
    }
}
