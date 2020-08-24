package cn.touchfish.mm.framework.entity;

import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: yp
 */
public class MvcMethod {
    private Method method;  //Method
    private Object obj;    //调用方法, 需要的对象
    private Class clazz;  //当前类的字节码对象

    public MvcMethod() {
    }

    public MvcMethod(Method method, Object obj, Class clazz) {
        this.method = method;
        this.obj = obj;
        this.clazz = clazz;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}
