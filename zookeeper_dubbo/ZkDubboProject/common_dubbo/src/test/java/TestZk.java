import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

/**
 * @ClassName TestZk
 * @Description Details
 * @Author Josen
 * @Create 2020/9/4 16:13
 */
public class TestZk {
    @Test
    public void test() throws Exception {
//        //1.创建重试对象
//        ExponentialBackoffRetry backoffRetry = new ExponentialBackoffRetry(5000, 3);
//        //2.创建客户端对象
//        CuratorFramework client = CuratorFrameworkFactory.newClient("hadoop128:2181", backoffRetry);
//        //3.开启客户端
//        client.start();
//        //4.获取节点数据放到props对象中
//        String driver = new String(client.getData().forPath("/jdbcConfig/jdbc.driver"));
//        String url = new String(client.getData().forPath("/jdbcConfig/jdbc.url"));
//        String user = new String(client.getData().forPath("/jdbcConfig/jdbc.user"));
//        String password = new String(client.getData().forPath("/jdbcConfig/jdbc.password"));
//
//        System.out.println(driver);
//        System.out.println(url);
//        System.out.println(user);
//        System.out.println(password);
//
//        client.close();
    }
}
