package com.use_aspectJ.annotation;
import com.use_aspectJ.intefaces.Calculator;
import org.springframework.stereotype.Component;

/**
 * @ClassName CalculatorImpl
 * @Description
 * @Author Josen
 * @Create 2020/7/25 19:39
 */

@Component
public class CalculatorImpl implements Calculator {
    @Override
    public int add(int i, int j) {
        System.out.println("run add...");
        return i+j;
    }
    @Override
    public int sub(int i, int j) {
        System.out.println("run sub...");
        return i-j;
    }

    @Override
    public int div(int i, int j) {
        System.out.println("run div...");
        return i/j;
    }

    @Override
    public int mul(int i, int j) {
        System.out.println("run mul...");
        return i*j;
    }
}
