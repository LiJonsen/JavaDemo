package cn.touchfish.mm.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: Controller注解
 * @Author: yp
 */
@Target(ElementType.TYPE) //只能用在类(接口)上面
@Retention(RetentionPolicy.RUNTIME) //任何阶段都有效
public @interface Controller {
}
