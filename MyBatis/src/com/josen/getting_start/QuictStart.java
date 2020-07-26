package com.josen.getting_start;

import com.josen.getting_start.bean.Job;
import com.josen.getting_start.mapper.JobsMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import utils.CommonUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName QuictStart
 * @Description Mybatis 快速使用
 *              完成对jobs表的增删改查操作
 * @Author Josen
 * @Create 20:21 20:21
 */
public class QuictStart {

    @Test
    public void testSqlMapParams(){
        SqlSession sqlSession = null;
        try{
             sqlSession = CommonUtils.createSqlSession();

            JobsMapper mapper = sqlSession.getMapper(JobsMapper.class);
//            Job job = mapper.queryByJobIdAndMaxSalary("AC_ACCOUNT", 5000.0);
            HashMap<String, Object> map = new HashMap<>();
            map.put("title","A");
            map.put("salary",5000);
            List<Job> jobs = mapper.queryJobForMap(map);
            jobs.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(sqlSession!=null)
                sqlSession.close();
        }
    }
    @Test
    public void testMyBatisMapper() throws IOException {
        // 1. 加载MyBatis全局配置文件
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 2. 获取SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 3. SqlSessionFactory生成一个SQL会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4. 获取EmployeeMapper接口代理类
        JobsMapper mapper = sqlSession.getMapper(JobsMapper.class);
        // 5. 调用EmployeeMapper方法，传入参数并执行SQL语句
        // --------------select---------------------
//        Job employee = mapper.queryOne("AC_MGR");
//        List<Job> list = mapper.queryAll();
//        System.out.println(list);
        // --------------insert---------------------
//        Boolean res1 = mapper.insertOne(new Job("AV_TEST", "TEST_01", 5888.88, 8888.88));
//        System.out.println(res1);
        // --------------update---------------------
//        Boolean res2 = mapper.updateOne(new Job("AV_TEST", "UPDATE_TEST", 5888.88, 8888.88));
//        System.out.println(res2);
        // --------------delete---------------------
        Boolean res3 = mapper.deleteOne("AV_TEST");
        System.out.println(res3);
        // 默认为手动提交，insert/delete/update操作需要调用commit()方法
        sqlSession.commit();
        sqlSession.close();
    }

}
