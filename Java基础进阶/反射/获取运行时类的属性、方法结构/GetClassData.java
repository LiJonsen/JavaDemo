package Reflection;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

// 获取运行时类的完整结构
public class GetClassData {
    @Test
    public void test(){
        Class<Person> clazz = Person.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        for(Field field : declaredFields){
            // 1. 获取权限修饰符（默认返回int，对应Modifier类中的常量）
            // Modifier.toString(modifiers)：转换成对应的权限修饰符
            int modifiers = field.getModifiers();
            System.out.print("权限修饰符:"+ Modifier.toString(modifiers)+"\t");

            // 2. 获取变量类型
            Class<?> type = field.getType();
            System.out.print("变量类型："+(type.getName())+"\t");

            // 3. 获取变量名
            String name = field.getName();
            System.out.print("变量名："+name);
            System.out.println();
        }
    }
    // 获取运行时对象的方法及其属性
    @Test
    public void testGetMethods(){
        Class<Person> clazz = Person.class;

        Method[] methods = clazz.getDeclaredMethods();
        /**
         * 打印结果：
         * public	void	setName	(java.lang.String args_0)
         * @Reflection.MyAnnotation(value=Hello)
         * public    void	show	()
         * public	int	getAge	()
         * public	void	setAge	(int args_0)
         * @Reflection.MyAnnotation(value=Yoo Bro!)
         * private	java.lang.String	getDesc	(java.lang.String args_0, boolean args_1, int args_2) throws java.lang.NullPointerException, java.lang.ClassCastException
         */
        for(Method method : methods){
            // 1. 获取方法声明的注解
            Annotation[] annotations = method.getAnnotations();
            for(Annotation annot : annotations){
                System.out.println(annot);
            }
            // 2. 获取方法权限修饰符
            int modifiers = method.getModifiers();
            System.out.print(Modifier.toString(modifiers)+"\t");

            // 3. 获取方法返回值类型
            System.out.print(method.getReturnType().getName()+"\t");

            // 4. 获取方法名
            System.out.print(method.getName()+"\t");

            // 5. 获取方法形参列表
            System.out.print("(");
            Class[] types = method.getParameterTypes();
            int i = 0;
            if(types!=null && types.length>0){
                for(Class type : types){
                    String str = type.getName()+" args_"+(i++);
                    if(i<types.length)
                        str+=", ";
                    System.out.print(str);
                }
            }

            System.out.print(")");
            i = 0;
            // 6. 获取方法throws异常类型
            Class[] exceptionTypes = method.getExceptionTypes();
            if(exceptionTypes!=null && exceptionTypes.length>0){
                System.out.print(" throws ");
                for(Class e : exceptionTypes){
                    String str = e.getName();
                    if((i++)<exceptionTypes.length-1)
                        str+=", ";
                    System.out.print(str);
                }
            }
            System.out.println();
        }
    }
}
