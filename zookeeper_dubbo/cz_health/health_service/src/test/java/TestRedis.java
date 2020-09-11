import com.josen.utils.DateUtils;
import com.josen.utils.QiNiuUtil;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @ClassName TestRedis
 * @Description Details
 * @Author Josen
 * @Create 2020/9/8 15:41
 */
public class TestRedis {
    @Test
    public void testing(){
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-tx.xml");
//        JedisPool jedisPool = context.getBean("jedisPool", JedisPool.class);
//        System.out.println(jedisPool);
//        Jedis resource = jedisPool.getResource();
//        System.out.println(resource.ping());
    }
//    @Test
//    public void testQn(){
//        List<String> list = QiNiuUtil.getBatchQiNiuImg();
//        System.out.println(list);
//    }

}
