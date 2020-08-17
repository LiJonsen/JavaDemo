package cn.touchfish.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName MapperUtil
 * @Description Details
 * @Author Josen
 * @Create 2020/8/14 23:30
 */
public final class MapperUtil {
    private static SqlSessionFactory build;
    private static SqlSession sqlSession;

    static {
        try (
                InputStream stream = Resources.getResourceAsStream("mybatis-config.xml")
         ){
            build = new SqlSessionFactoryBuilder().build(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Mapper
     * @param clazz
     * @param <E>
     * @return
     */
    public static <E> E getSqlMapper(Class<E> clazz) {
        sqlSession = build.openSession();
        E mapper = sqlSession.getMapper(clazz);
        return mapper;
    }
    // 提交事务
    public static void commitTransaction(){
        sqlSession.commit();
    }
    // 关闭SqlSession
    public static void closeSqlSession(){
        sqlSession.close();
    }
}
