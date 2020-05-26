package JDBC_test.JunitForDao;

import JDBC_test.Utils.DAO_JDBC_Utils;
import JDBC_test.bean.Customer;
import JDBC_test.dao.CustomerDaoImpl;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * 測試customers數據表DAO封裝
 */
public class Test01 {
    CustomerDaoImpl dao = new CustomerDaoImpl();
    @Test
    public void testing(){
        Connection conn = null;
        try {
            conn = DAO_JDBC_Utils.getConnection();

            // 設置手動提交SQL
            conn.setAutoCommit(false);
            // 1. 查詢總數量
//            Number count = dao.getCount(conn);
//            System.out.println(count);

            // 2. 插入一條記錄
//            Customer josen = new Customer(1, "Josen", "josen@gmail.com", new Date(123787852L));
//            boolean insert = dao.insert(conn, josen);
//            System.out.println(insert?"插入成功":"插入失敗");

            // 3. 查詢一條數據
//            Customer customer = dao.getCustomerById(conn, 20);
//            System.out.println(customer);

            // 4. 查詢所有數據
//            List<Customer> list = dao.getAll(conn);
//            list.forEach(System.out::println);

            // 5. 獲取最大birth生日日期
//            Date maxDate = dao.getMaxDate(conn);
//            System.out.println(maxDate);

            // 6. 修改指定Customer的信息
            // Error：java.sql.SQLException: Incorrect string value: '\xE9\xA6\xB3' for column 'name' at row 1
            // 解決（修改表編碼）：ALTER TABLE customers CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
            Customer zxc = new Customer(22, "周星馳", "zxc@gmail.com", new Date(123787852L));
            boolean b1 = dao.modifyById(conn, zxc);
            System.out.println(b1?"修改成功":"修改失敗");

            conn.commit();
        } catch (Exception e) {
            DAO_JDBC_Utils.rollback(conn);
            e.printStackTrace();
        }finally {

            DAO_JDBC_Utils.closeConn(conn);
        }

    }
}
