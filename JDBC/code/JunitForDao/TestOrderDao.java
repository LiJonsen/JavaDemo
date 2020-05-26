package JDBC_test.JunitForDao;

import JDBC_test.DruidDao.OrderDao;


import JDBC_test.Utils.DAO_JDBC_Utils;
import JDBC_test.bean.Order;
import org.junit.Test;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * Druid数据库连接池+DBUtils工具类+DAO优化
 * 测试
 */
public class TestOrderDao {
    private static OrderDao dao = new OrderDao();
    @Test
    public void testing(){
        Connection conn = null;
        try {
            conn = DAO_JDBC_Utils.getDruidConn();

            // 1. 查询一条记录
//            Order order = dao.QueryBean(conn, 4);

            // 2. 新增一条记录
//            Order o1 = new Order(1, "TestOrder1", new Date(123787852L));
//            int i = dao.addOrder(conn, o1);
//            System.out.println(i);

            // 3. 删除一条记录
//            int i2 = dao.deleteOrder(conn, 8);
//            System.out.println(i2);

            // 4. 获取所有记录
            List<Order> orders = dao.queryAll(conn);
            orders.forEach(System.out::println);

            // 5. 修改一条记录
            Order cc = new Order(2, "CC", new Date(123787852L));
            int i3 = dao.modifyOrder(conn, cc);
            System.out.println(i3);

            // 6. 获取order记录总数
            long count = dao.getOrderCount(conn);
            System.out.println(count);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DAO_JDBC_Utils.closeConn(conn);
        }
    }
}
