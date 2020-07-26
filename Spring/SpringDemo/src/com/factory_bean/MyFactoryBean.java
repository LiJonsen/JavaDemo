package com.factory_bean;

import com.beans.Car;
import org.springframework.beans.factory.FactoryBean;

/**
 * @ClassName MyFactoryBean
 * @Description 工厂bean
 * @Author Josen
 */
public class MyFactoryBean implements FactoryBean<Car> {

    /**
     * 将创建好的bean返回给IOC容器
     * @return
     * @throws Exception
     */
    @Override
    public Car getObject() throws Exception {
        return new Car("法拉利",888888.88);
    }

    /**
     * 返回bean类型
     * @return
     */
    @Override
    public Class<Car> getObjectType() {
        return Car.class;
    }

    /**
     * 创建的bean是否是单例模式
     * @return
     */
    @Override
    public boolean isSingleton() {
        return false;
    }
}
