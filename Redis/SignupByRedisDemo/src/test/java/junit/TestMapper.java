package junit;

import cn.touchfish.beans.HomeUser;
import cn.touchfish.beans.User;
import cn.touchfish.mapper.HomeMapper;
import cn.touchfish.mapper.UserMapper;
import cn.touchfish.utils.MapperUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TestMapper
 * @Description Details
 * @Author Josen
 * @Create 2020/8/14 20:47
 */
public class TestMapper {
//    @Before
//    public void init() throws IOException {
//        stream = Resources.getResourceAsStream("mybatis-config.xml");
//        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(stream);
//        sqlSession = build.openSession();
//    }

    @Test
    public void testMapperUtil(){

    }

//    @After
//    public void destroy() throws IOException {
//        stream.close();
//        sqlSession.close();
//    }
}
