package CGlibDecimalProxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * @ClassName TestCGlib
 * @Description Details
 * @Author Josen
 * @Create 2020/7/25 23:00
 */
public class TestCGlib {
    public static void main(String[] args) {
//        MyDecimalProxy proxy = new MyDecimalProxy();
//
//        Class[] argumentTypes = new Class[]{String.class,Integer.class};
//        Object[] arguments = new Object[]{"James",38};
//        Employee emp = (Employee)proxy.getProxy(Employee.class,argumentTypes,arguments);

        //实例化一个增强器，也就是cglib中的一个class generator
        Enhancer eh = new Enhancer();
        //设置目标类
        eh.setSuperclass(Employee.class);
        // 设置拦截对象
        eh.setCallback(new MyDecimalProxy());
        // 生成代理类并返回一个实例
        Employee emp = (Employee) eh.create();
//        System.out.println(emp);
        emp.sayHello();
    }
}
