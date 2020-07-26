package com.bean_lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @ClassName BeanPostProcessor
 * @Description bean的后置处理器（对IOC容器中所有bean都起作用）
 * @Author Josen
 * @Create 10:57 10:57
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    /**
     * 在bean的生命周期初始化方法执行之前调用
     * @param bean 正在被创建的bean对象
     * @param beanName bean对象的名字（配置的id值）
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Init bean before...");
        return bean;
    }

    /**
     * 在bean的声明周期初始化方法执行之后调用
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Init bean after...");

        return bean;
    }
}
