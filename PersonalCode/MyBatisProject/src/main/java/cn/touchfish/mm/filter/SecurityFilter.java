package cn.touchfish.mm.filter;

import cn.touchfish.mm.entity.Result;
import cn.touchfish.mm.framework.annotation.Controller;
import cn.touchfish.mm.framework.annotation.PreAuthorize;
import cn.touchfish.mm.framework.annotation.RequestMapping;
import cn.touchfish.mm.framework.utils.ClassScannerUtils;
import cn.touchfish.mm.pojo.User;
import cn.touchfish.mm.utils.JsonUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SecurityFilter
 * @Description 权限校验过滤器
 * @Author Josen
 * @Create 2020/8/22 14:32
 */
public class SecurityFilter implements Filter {
    // 缓存Filter初始化时，加载@Controller注解下所有的@PreAuthorize权限注解信息
    // key=路径，value=访问当前路径所需权限值
    private Map<String,String> cachePermissions = new HashMap<>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        // 加载@Controller下所有的@PreAuthorize权限注解信息
        String scannerPackage = filterConfig.getInitParameter("scannerPackage");
        System.out.println("SecurityFilter init...");
        // 1. 扫描@Controller
        List<Class<?>> classList = ClassScannerUtils.getClasssFromPackage(scannerPackage);

        for (Class<?> clazz : classList) {
            // 2. 判断当前类是否拥有@Controller注解
            if(clazz.isAnnotationPresent(Controller.class)){
                // 3. 获取当前类下的所有Method
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    boolean hasAuthorize = method.isAnnotationPresent(PreAuthorize.class);
                    boolean hasReqMapper = method.isAnnotationPresent(RequestMapping.class);
                    // 4. 判断当前方法必须拥有@PreAuthorize和@RequestMapping两个注解
                    if(hasAuthorize && hasReqMapper){
                        // 5. 获取注解中的值
                        String pre_val = method.getAnnotation(PreAuthorize.class).value();
                        String req_val = method.getAnnotation(RequestMapping.class).value();
                        // 6. 添加到cachePermissions中（req_val=路径，pre_val=访问当前路径所需权限值）
                        cachePermissions.put(req_val,pre_val);
                    }
                }
            }
        }

        System.out.println("SecurityFilter cachePermissions="+cachePermissions);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        // 1. 获取当前请求路径信息
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String url = uri.substring(contextPath.length());

        // 处理后缀为.do的请求
        if(url.endsWith(".do") && !url.contains("wxapi")){
            url = url.replace(".do","");
            // 2. 获取用户Session信息，拿到用户拥有的权限列表
            User user = (User) req.getSession().getAttribute("user");
            if(user!=null && user.getAuthorityList()!=null){
                List<String> authorityList = user.getAuthorityList();
                String curPermission = cachePermissions.get(url);
                // 3. 判断当前url有没有设置权限
                if(curPermission!=null && !"".equals(curPermission)){
                    if(!authorityList.contains(curPermission)){
                        // 4. 没有权限，拦截返回
                        JsonUtils.printResult(resp,new Result(500,"当前账号没有该资源的访问权限！"));
                        return;
                    }
                }
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("SecurityFilter destroy...");
    }
}
