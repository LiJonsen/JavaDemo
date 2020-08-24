package cn.touchfish.mm.framework;

import cn.touchfish.mm.framework.annotation.Controller;
import cn.touchfish.mm.framework.annotation.RequestMapping;
import cn.touchfish.mm.framework.entity.MvcMethod;
import cn.touchfish.mm.framework.utils.ClassScannerUtils;
import org.junit.Test;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 存在问题:
 * 1. controller包里面任何一个类都会扫描(获得类里面的Method), 加载 --> 扫描太多
 * 2. 等来了请求再去解析,再去反射创建对象调用 ---> 影响处理请求的速度的
 * 3. 扫描的包名写死了
 * 解决:
 * 1.只扫描这个类上面有Controller注解的(获得类里面的Method)
 * 2.提前(服务器启动的时候)解析好, 把对象创建好, method获得好 存到容器【Map】, 等来了请求的时候 根据映射路径(eg: /user/login)直接从容器里面获得对应的Method调用
 * map的key是RequestMapping的value, map的值是对应的MvcMethod
 * 3.配置Servlet的初始化参数
 */
public class DispatcherServlet extends HttpServlet {

    private Map<String, MvcMethod> methodMap = new HashMap<String, MvcMethod>();

    @Override
    //解析好, 把对象创建好, method获得好 存到容器【Map】
    public void init(ServletConfig config) throws ServletException {
        try {
            super.init(config);
            //1.扫描某个包里面的所有类的字节码对象集合List
            String packageScan = config.getInitParameter("packageScan");
            String wxApiPackage = config.getInitParameter("WxApiPackage");
            List<Class<?>> managerApiList = ClassScannerUtils.getClasssFromPackage(packageScan);
            List<Class<?>> wxApiList = ClassScannerUtils.getClasssFromPackage(wxApiPackage);
            List<Class<?>> classList = Stream.concat(managerApiList.stream(), wxApiList.stream()).collect(Collectors.toList());
            System.out.println(classList);
            //2.遍历字节码对象集合List
            for (Class<?> clazz : classList) {
                if (clazz.isAnnotationPresent(Controller.class)) { //判断Controller类上面是否有Controller注解
                    //3.获得类里面的所有的Method
                    Method[] methods = clazz.getDeclaredMethods();
                    //4.遍历所有的Method
                    for (Method method : methods) {
                        //5.获得method上面的RequestMapping注解 获得注解的value属性值
                        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                        if (requestMapping != null) {
                            String value = requestMapping.value();
                            //6.存到容器Map里面(map的key是RequestMapping的value, map的值是对应的MvcMethod)
                            MvcMethod mvcMethod = new MvcMethod(method, clazz.newInstance(), clazz);
                            methodMap.put(value, mvcMethod);
                        }

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //1.获得请求的URI和项目部署路径, 截取获得映射路径 eg: /user/login
            String requestURI = request.getRequestURI();
            String contextPath = request.getContextPath();

            String mappingPath = requestURI.substring(contextPath.length(), requestURI.lastIndexOf(".")); //  /user/login

            //2.根据映射路径 从Map获得对应的方法调用
            MvcMethod mvcMethod = methodMap.get(mappingPath);
            if (mvcMethod != null) {
                mvcMethod.getMethod().invoke(mvcMethod.getObj(), request, response);
            }else{
				throw new RuntimeException("找不到资源" + mappingPath);
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
