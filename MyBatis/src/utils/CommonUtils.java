package utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName CommonUtils
 * @Description 工具类
 * @Author Josen
 * @Create 14:47 14:47
 */
public final class CommonUtils {
    // 获取SqlSession
    public static SqlSession createSqlSession() throws IOException {
        // 1. 加载MyBatis全局配置文件
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 2. 获取SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 3. SqlSessionFactory生成一个SQL会话
        return sqlSessionFactory.openSession();
    }
}
