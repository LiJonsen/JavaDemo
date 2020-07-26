package Class_day7_13.dynamicProxy;

import Class_day7_13.dynamicProxy.interfaces.NikeInterface;

/**
 * @ClassName NikeFactory
 * @Description Nike工厂被代理类
 * @Author Josen
 * @Create 17:27 17:27
 */
public class NikeFactory implements NikeInterface {
    @Override
    public void startProduce() {
        System.out.println("Nike工厂开始生产球鞋...");
    }
}
