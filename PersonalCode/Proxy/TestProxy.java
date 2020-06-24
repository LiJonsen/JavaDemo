package com.review.Proxy;

import org.junit.Test;

/**
 * @ClassName TestProxy
 * @Description TODO
 * @Author Josen
 * @Date 2020/6/23 20:13
 * @Version 1.0
 **/
public class TestProxy {
    @Test
    public void testing(){
        Human human = new SuperMan();
        Human proxy_human = (Human)ProxyFactory.getProxyInstance(human);

        proxy_human.show();
        proxy_human.eat("banana");
    }
}
