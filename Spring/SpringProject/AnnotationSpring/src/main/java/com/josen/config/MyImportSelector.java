package com.josen.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @ClassName MyImportSelector
 * @Description 实现ImportSelector接口，重写selectImports方法
 * @Author Josen
 * @Create 2020/8/27 8:52
 */
public class MyImportSelector implements ImportSelector {
    /**
     * @param annotationMetadata。被@Import注解标注的类上所有注解的信息
     */
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{ApplicationConfig.class.getName()};
    }
}
