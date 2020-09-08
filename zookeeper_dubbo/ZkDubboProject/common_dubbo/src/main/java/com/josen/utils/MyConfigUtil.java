package com.josen.utils;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.util.Properties;

/**
 * @ClassName MyConfigUtil
 * @Description 配置工具类
 * 监听zookeeper中config变化，无缝更新JDBC连接配置
 * PropertyPlaceholderConfigurer：== context:property-placeholder 加载jdbc.properties类
 * @Author Josen
 * @Create 2020/9/4 15:40
 */
public class MyConfigUtil extends PropertyPlaceholderConfigurer implements ApplicationContextAware {
    // 注意：xmlWebApplicationContext为内部变量，不能加权限修饰符和修改变量名
    XmlWebApplicationContext xmlWebApplicationContext;
    // zookeeper 客户端
    private static CuratorFramework zkClient;
    // treeCache监听树
    private static TreeCache treeCache;

    static {
        System.out.println("============> run init method...");

        //1.创建重试对象
        ExponentialBackoffRetry backoffRetry = new ExponentialBackoffRetry(5000, 3);
        //2.创建客户端对象
        zkClient = CuratorFrameworkFactory.newClient("hadoop128:2181", backoffRetry);
        //3.开启zk客户端
        zkClient.start();

        try {
            treeCache = new TreeCache(zkClient, "/jdbcConfig");
            treeCache.start();
        } catch (Exception e) {
            System.out.println("============> treeCache error...");
            e.printStackTrace();
        }
    }


    /**
     * ApplicationContextAware-监听IOC容器初始化
     * 启动的时候 将spring容器赋值给当前类的对象
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        xmlWebApplicationContext = (XmlWebApplicationContext) applicationContext;
    }

    /**
     * 重写PropertyPlaceholderConfigurer父类方法 加载配置数据
     */
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        // 读取zookeeper配置，并设置到dataSource中
        loadZkJdbcConfig(props);
        // 监听zookeeper配置变化
        listenerZkJdbcConfig();
        // 调用父类
        super.processProperties(beanFactoryToProcess, props);
    }

    /**
     * 获取zookeeper中的配置信息
     */
    private void loadZkJdbcConfig(Properties props) {
        try {
            //4.获取节点数据放到props对象中
            String driver = new String(zkClient.getData().forPath("/jdbcConfig/jdbc.driver"));
            String url = new String(zkClient.getData().forPath("/jdbcConfig/jdbc.url"));
            String user = new String(zkClient.getData().forPath("/jdbcConfig/jdbc.user"));
            String password = new String(zkClient.getData().forPath("/jdbcConfig/jdbc.password"));
            //5.将zookeeper获取的数据设置到DataSource中
            props.setProperty("jdbc.driver", driver);
            props.setProperty("jdbc.url", url);
            props.setProperty("jdbc.user", user);
            props.setProperty("jdbc.password", password);
            System.out.println("============> 加载zookeeper jdbc初始化配置...");
        } catch (Exception e) {
            System.out.println("============> 连接zookeeper失败...");
            e.printStackTrace();
        }

    }

    /**
     * 监听zookeeper jdbcConfig变化
     * 通过刷新容器实现无缝更新配置
     */
    private void listenerZkJdbcConfig() {
        System.out.println("============> 执行listenerZkJdbcConfig监听...");
        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent event) throws Exception {
                // 监听zk更新，刷新IOC容器
                if (event.getType() == TreeCacheEvent.Type.NODE_UPDATED) {
                    System.out.println("============> 监听到zookeeper jdbcConfig数据更新，刷新IOC容器...");
                    xmlWebApplicationContext.refresh();
                }
            }
        });


    }


}
