package JDBC_test.dao;

import JDBC_test.Utils.DAO_JDBC_Utils;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装了针对数据表的通用操作（考虑数据库事务）
 * 抽象类：不可实例化，提供子类内部调用
 */
public abstract class BaseDao<T> {
    /**
     * @Description 获取一个但一值得方法，专门用来执行像 select count(*)...这样的sql语句
     * @param conn
     * @param sql
     * @param args
     * @return
     */
    public <E> E getValue(Connection conn,String sql,Object ...args){
        java.sql.PreparedStatement ps = null;
        try{
            // 2. 预编译SQL语句
            ps = conn.prepareStatement(sql);

            // 2.1 填充占位符（?）
            for(int i=0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            // 3. 执行SQL语句
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return (E) resultSet.getObject(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 4. 关闭资源、连接
            DAO_JDBC_Utils.closeConnections(ps);
        }

        return null;
    }
    /**
     * @Description 封装对数据表的增删改操作（考虑事务，Connection在外部关闭）
     * @param conn
     * @param sql
     * @param args
     * @return
     */
    public boolean updateSqlTable(Connection conn,String sql,Object ...args){
        boolean status = false;
        java.sql.PreparedStatement ps = null;
        try{
            // 2. 预编译SQL语句
            ps = conn.prepareStatement(sql);

            // 2.1 填充占位符（?）
            for(int i=0;i<args.length;i++){
                System.out.println(args[i]);
                ps.setObject(i+1,args[i]);
            }
            // 3. 执行SQL语句
            ps.execute();

            status = true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 4. 关闭资源、连接
            DAO_JDBC_Utils.closeConnections(ps);
        }

        return status;
    }

    /**
     * @Description 查询数据表记录
     * @param clazz
     * @param sql
     * @param args
     * @return
     */
    public List<T> getResult(Connection conn, Class<T> clazz, String sql, Object ...args){
        java.sql.PreparedStatement ps = null;
        ResultSet resultSet = null;
        try{
            // 2. 预编译SQL语句
            ps = conn.prepareStatement(sql);

            // 2.1 填充占位符（?）
            for(int i=0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }

            // 3. 执行SQL语句,获取结果集
            resultSet = ps.executeQuery();
            // 3.1 获取结果集的元数据
            ResultSetMetaData metaData = resultSet.getMetaData();

            // 获取列名个数
            int columnCount = metaData.getColumnCount();

            // 声明集合，用于存储查询返回的结果集
            ArrayList<T> ts = new ArrayList<>();
            // 3.2 next()方法判断是否还有下一条记录
            while(resultSet.next()){
                T obj = clazz.newInstance();
                // 3.3 通过反射动态的设置Customer属性
                for(int i=0;i<columnCount;i++){
                    String columnLabel = metaData.getColumnLabel(i+1);
                    Field declaredField = clazz.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    String columnTypeName = metaData.getColumnTypeName(i + 1);
                    // 处理Blob类型数据
                    if(columnTypeName.contains("BLOB")){
                        System.out.println(columnTypeName);
                        declaredField.set(obj,resultSet.getBlob(i+1));
                    }else{
                        declaredField.set(obj,resultSet.getObject(i+1));
                    }
                }
                ts.add(obj);
            }

            return ts;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 4. 关闭资源、连接
            DAO_JDBC_Utils.closeConnections(ps,resultSet);
        }
        return null;
    }
}
