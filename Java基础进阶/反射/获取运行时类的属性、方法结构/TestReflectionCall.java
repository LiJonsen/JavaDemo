package Reflection;
import org.junit.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 测试反射调用指定结构
 */
public class TestReflectionCall {
    // 1. 调用运行时类的指定属性
    @Test
    public void testReflectionAttr() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Class<Person> clazz = Person.class;
        Person person = clazz.newInstance();
        Field age = clazz.getDeclaredField("age");
        age.setAccessible(true);

        age.set(person,99);

        System.out.println(person);
    }
    // 2. 调用运行时类的指定方法
    @Test
    public void testReflectionMethod() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<Person> clazz = Person.class;
        Person person = clazz.newInstance();
        // 1. 调用Person类私有方法
        // getDeclaredMethod()：参数一=方法名，参数二=调用对应形参类型的方法
        Method getDesc = clazz.getDeclaredMethod("getDesc",String.class,boolean.class,int.class);
        getDesc.setAccessible(true);

        String invoke = (String) getDesc.invoke(person,"test",true,80);
        System.out.println(invoke);


        // 2. 调用Person类静态方法
        Method commonPrint = clazz.getDeclaredMethod("commonPrint");
        Object invoke1 = commonPrint.invoke(Person.class);
        System.out.println(invoke1);
    }
    // 3. 调用运行时类的指定构造器
    @Test
    public void testReflectionCons() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<Person> clazz = Person.class;
        // 获取指定形参类型构造器
        Constructor<Person> constructor = clazz.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        Person pack = constructor.newInstance("Pack");
        System.out.println(pack);
    }

}
