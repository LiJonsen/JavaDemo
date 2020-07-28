package com.use_aspectJ;

import com.use_aspectJ.intefaces.Calculator;
import com.utils.CommonUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

/**
 * @ClassName TestAspectJ
 * @Description Details
 * @Author Josen
 * @Create 2020/7/27 12:43
 */
public class TestAspectJ {
    @Test
    public void testing(){
        ApplicationContext context = CommonUtils.getApplicationContext("aspectJ-config.xml");

        Calculator bean = context.getBean("calculatorImpl", Calculator.class);
        System.out.println(bean.getClass().getName());
        int res = bean.add(1, 1);
        System.out.println("result="+res);

//        int res2 = bean.div(5, 0);
//        System.out.println("result2="+res2);


    }
    @Test
    public void testConfigForXml(){
        ApplicationContext context = CommonUtils.getApplicationContext("aspectJ-config-xml.xml");
        Calculator bean = context.getBean("calculatorImpl", Calculator.class);
        int res = bean.add(2, 12);
        System.out.println("result="+res);

        
    }
}
