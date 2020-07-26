#### 一、概述

> 动态代理：在程序运行时，通过反射机制根据指定的被代理类动态生成代理类，从而实现在调用原有方法的基础上做一些其他操作；
>
> 
>
> 分类：
>
> * 基于接口的JDK动态代理 ：
>   * 使用Java提供的 `Proxy` 和 `InvocationHandler `实现动态代理；
>   * 特点： 被代理类必须实现某个指定的接口，通过该接口动态的生成实现了该接口的代理类；
>   * 缺点：只能代理实现了接口的类；
>
> * 基于类的CGlib动态代理 ：
>   * CGlib是一种第三方的代理模式，需要导入jar包；
>     * asm-7.0.jar
>     * cglib-3.2.10.jar
>   * 特点：`不需要提供接口`，针对类来实现代理，原理是对指定的`目标类`生成一个`子类`，并覆盖其中方法实现增强，但因为采用的是继承，所以不能对`final修饰`的类进行代理 ；



#### 二、简单版计算机（基于动态代理实现）

>  要求：
>
> ​    ①执行加减乘除运算
>
> ​    ②日志：在程序执行期间追踪正在发生的活动【即执行运算方法时，前后加入日志打印】
>
> ​    ③验证：能处理正数的运算就行了

<img src=".\imgs\img1.png"/>



##### 2.1 基于接口的JDK动态代理





#### 四、动态代理的原理







#### 五、反编译动态代理类到本地

> 将动态代理所生成的代理类class持久化到本地，查看其源代码结构；
>
> 【需要用到idea反编译工具】



添加配置

```java
public class Testing {
    public static void main(String[] args) {
		// 添加如下配置
        Properties properties = System.getProperties();
        properties.put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //---------------------------------
        Calculator calculator = new CalculatorImpl();
        Calculator proxy = DecimalProxy.getProxyByInterface(calculator);
        proxy.add(1,1);
    }
}
```

编译代码后，项目会自动生成一个`com/sun/proxy`目录，该目录下就是内存里动态生成的代理类代码；



```java

package com.sun.proxy;

import JdkDecimalProxy.intefaces.Calculator;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

public final class $Proxy0 extends Proxy implements Calculator {
    private static Method m1;
    private static Method m2;
    private static Method m6;
    private static Method m3;
    private static Method m5;
    private static Method m4;
    private static Method m0;

    public $Proxy0(InvocationHandler var1) throws  {
        super(var1);
    }

    public final boolean equals(Object var1) throws  {
        try {
            return (Boolean)super.h.invoke(this, m1, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final String toString() throws  {
        try {
            return (String)super.h.invoke(this, m2, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final int mul(int var1, int var2) throws  {
        try {
            return (Integer)super.h.invoke(this, m6, new Object[]{var1, var2});
        } catch (RuntimeException | Error var4) {
            throw var4;
        } catch (Throwable var5) {
            throw new UndeclaredThrowableException(var5);
        }
    }

    public final int add(int var1, int var2) throws  {
        try {
            return (Integer)super.h.invoke(this, m3, new Object[]{var1, var2});
        } catch (RuntimeException | Error var4) {
            throw var4;
        } catch (Throwable var5) {
            throw new UndeclaredThrowableException(var5);
        }
    }

    public final int sub(int var1, int var2) throws  {
        try {
            return (Integer)super.h.invoke(this, m5, new Object[]{var1, var2});
        } catch (RuntimeException | Error var4) {
            throw var4;
        } catch (Throwable var5) {
            throw new UndeclaredThrowableException(var5);
        }
    }

    public final int div(int var1, int var2) throws  {
        try {
            return (Integer)super.h.invoke(this, m4, new Object[]{var1, var2});
        } catch (RuntimeException | Error var4) {
            throw var4;
        } catch (Throwable var5) {
            throw new UndeclaredThrowableException(var5);
        }
    }

    public final int hashCode() throws  {
        try {
            return (Integer)super.h.invoke(this, m0, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    static {
        try {
            m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
            m2 = Class.forName("java.lang.Object").getMethod("toString");
            m6 = Class.forName("JdkDecimalProxy.intefaces.Calculator").getMethod("mul", Integer.TYPE, Integer.TYPE);
            m3 = Class.forName("JdkDecimalProxy.intefaces.Calculator").getMethod("add", Integer.TYPE, Integer.TYPE);
            m5 = Class.forName("JdkDecimalProxy.intefaces.Calculator").getMethod("sub", Integer.TYPE, Integer.TYPE);
            m4 = Class.forName("JdkDecimalProxy.intefaces.Calculator").getMethod("div", Integer.TYPE, Integer.TYPE);
            m0 = Class.forName("java.lang.Object").getMethod("hashCode");
        } catch (NoSuchMethodException var2) {
            throw new NoSuchMethodError(var2.getMessage());
        } catch (ClassNotFoundException var3) {
            throw new NoClassDefFoundError(var3.getMessage());
        }
    }
}
```

