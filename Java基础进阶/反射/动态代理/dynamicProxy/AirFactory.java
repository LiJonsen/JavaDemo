package Class_day7_13.dynamicProxy;

import Class_day7_13.dynamicProxy.interfaces.AirInterface;

/**
 * @ClassName AirFactory
 * @Description Details
 * @Author Josen
 * @Create 18:21 18:21
 */
public class AirFactory implements AirInterface {
    @Override
    public void produceClothes() {
        System.out.println("Air工厂开始生产衣服...");
    }
}
