package JDBC_test.BlobTest;

import JDBC_test.Utils.CommonUtil;
import JDBC_test.Utils.JDBC_Utils;
import JDBC_test.Utils.QueryForTables;
import JDBC_test.bean.Customer;
import org.junit.Test;

import java.io.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * 使用Blob格式操作数据库图片
 */
public class blobTesting {
    /**
     * 测试获取数据表中的Blob格式图片，并保存到本地
     */
    @Test
    public void testing2(){
        String sql = "select name,email,birth,photo from customers where id = ?";
        List<Customer> result = QueryForTables.getResult(Customer.class, sql, 16);
        Customer customer = result.get(0);
        Blob photo = customer.getPhoto();

        // 将图片保存到本地
        InputStream is = null;
        OutputStream os = null;
        try{
             is = photo.getBinaryStream();
             os = new FileOutputStream("zhuyin.jpg");
            byte [] buffer = new byte[1024];
            int len = 0;
            while((len = is.read(buffer)) != -1){
                os.write(buffer, 0, len);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
    /**
     * 测试新增一条记录，并插入Blob格式的图片到数据表中
     */
    @Test
    public void testing() {
        FileInputStream fis = null;
        Connection conn = null;
        java.sql.PreparedStatement ps = null;
        try {
            String sql = "insert into customers(name,email,birth,photo) values(?,?,?,?)";
            // 1. 获取连接
            conn = JDBC_Utils.getConnection();
            // 2. 预编译SQL语句
            ps = conn.prepareStatement(sql);

            // 2.1 填充占位符（?）
            ps.setString(1,"Nuxt");
            ps.setString(2,"Nuxt@gmail.com");

            long dateMillis = CommonUtil.getDateMillis("2018-3-23");
            ps.setDate(3,new Date(dateMillis));

            fis = new FileInputStream("src/JDBC_test/nuxt.jpg");
            ps.setBlob(4,fis);

            // 3. 执行SQL语句
            ps.execute();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            // 4. 关闭资源、连接
            JDBC_Utils.closeConnections(conn,ps);
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
