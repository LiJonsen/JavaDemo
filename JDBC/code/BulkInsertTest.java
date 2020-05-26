package JDBC_test;

import JDBC_test.Utils.JDBC_Utils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * 批量插入示例
 */
public class BulkInsertTest {
    /*
     * 使用 addBatch() / executeBatch() / clearBatch()
     * mysql服务器默认是关闭批处理的，我们需要通过一个参数，让mysql开启批处理的支持。
     * 		 ?rewriteBatchedStatements=true 写在配置文件的url后面
     * 使用Connection 的 setAutoCommit(false)  /  commit()
     */
    @Test
    public void testing(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            conn = JDBC_Utils.getConnection();
            String sql = "insert into test_insert(name) values(?)";
            ps = conn.prepareStatement(sql);
            // 默认为true：自动提交
            conn.setAutoCommit(false);

            for(int i=1;i<=1000000;i++){
                // 填充SQL占位符
                ps.setString(1,("name_"+i));

                // 积攒SQL
                ps.addBatch();

                if(i % 500 == 0){
                    // 批量执行SQL
                    ps.executeBatch();

                    // 清理SQL
                    ps.clearBatch();
                }
            }
            // 手动提交
            conn.commit();
            long end = System.currentTimeMillis();

            System.out.println(end-start); //7161
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBC_Utils.closeConnections(conn,ps);
        }

    }
}
