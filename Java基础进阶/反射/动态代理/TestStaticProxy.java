package Reflection;

/**
 * 静态代理示例
 */
interface clothFactory{
   void produceFactory();
}

class ProxyClothFactory implements clothFactory{
    private clothFactory factory;
    ProxyClothFactory(clothFactory factory){
        this.factory = factory;
    }
    @Override
    public void produceFactory() {
        System.out.println("代理Nike工厂做一下准备工作");
        factory.produceFactory();
        System.out.println("代理Nike工厂做一下后续工作");

    }
}

class NikeClothFactory implements clothFactory{
    @Override
    public void produceFactory() {
        System.out.println("Nike工厂开始制作衣服");
    }
}

public class TestStaticProxy {
    public static void main(String[] args) {
        // 创建被代理实例对象
        NikeClothFactory nike = new NikeClothFactory();
        // 创建代理类实例对象
        ProxyClothFactory proxyClothFactory = new ProxyClothFactory(nike);
        proxyClothFactory.produceFactory();
    }
}
