package cn.touchfish.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName MyC3p0Pool
 * @Description C3p0连接池单例
 * @Author Josen
 * @Create 2020/8/14 16:24
 */
public final class MyC3p0Pool {
    private static ComboPooledDataSource dataSource;

    /**
     * 获取单例连接池
     * @return
     */
    public static ComboPooledDataSource getDataSource(){
        System.out.println("Execute Get C3p0 Poll...");

        if(dataSource == null){
            synchronized (MyC3p0Pool.class){
                if(dataSource==null){
                    System.out.println("Execute Init C3p0 Poll...");

                    dataSource = new ComboPooledDataSource();
                    settingConfig();
                }
            }
        }
        return dataSource;
    }

    /**
     * 配置连接池信息
     */
    private static void settingConfig(){
        // 配置c3p0连接池
        Properties properties = new Properties();
        InputStream stream = null;
        try {
            // getResource - 每次都是获取最新的文件
            stream = MyC3p0Pool.class.getClassLoader().getResource("jdbc.properties").openStream();
            // getResourceAsStream - 优先在缓存中读取文件
//        InputStream stream = JdbcUtils.class.getResourceAsStream("jdbc.properties");
            properties.load(stream);
            String max = properties.getProperty("db_maxPoolSize");
            String min = properties.getProperty("db_minPoolSize");
            dataSource.setMaxPoolSize(Integer.parseInt(max));
            dataSource.setMinPoolSize(Integer.parseInt(min));
            dataSource.setDriverClass(properties.getProperty("driverClass"));
            dataSource.setJdbcUrl(properties.getProperty("dev_url"));
            dataSource.setUser(properties.getProperty("dev_username"));
            dataSource.setPassword(properties.getProperty("dev_password"));

            properties.clear();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
           CommonUtils.closeStream(stream);
        }
    }

}
