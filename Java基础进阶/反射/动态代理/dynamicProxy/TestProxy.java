package Class_day7_13.dynamicProxy;
import Class_day7_13.dynamicProxy.interfaces.AirInterface;
import Class_day7_13.dynamicProxy.interfaces.NikeInterface;
import org.junit.Test;

/**
 * @ClassName TestProxy
 * @Description 测试动态代理
 * @Author Josen
 * @Create 17:27 17:27
 */
public class TestProxy {
    public static void main(String[] args) {
        // 1 创建被代理类实例
        NikeFactory nikeFactory = new NikeFactory();

        // 2 调用getProxy()获取动态生成的代理类
        NikeInterface nike = ProxyFactory.getProxy(nikeFactory);

        // 3. 使用代理类调用方法
        nike.startProduce();

        // ----------------第二个被代理类-------------------
        // 1 创建被代理类实例
        AirInterface airFactory = new AirFactory();

        // 2 调用getProxy()获取动态生成的代理类
        AirInterface air = ProxyFactory.getProxy(airFactory);

        // 3. 使用代理类调用方法
        air.produceClothes();
    }

}
