package JdkDecimalProxy.beans;

import JdkDecimalProxy.intefaces.Calculator;

/**
 * @ClassName CalculatorImpl
 * @Description
 * @Author Josen
 * @Create 2020/7/25 19:39
 */
public class CalculatorImpl implements Calculator {
    @Override
    public int add(int i, int j) {
        System.out.println("run add...");
        return i+j;
    }
    @Override
    public int sub(int i, int j) {
        return i-j;
    }

    @Override
    public int div(int i, int j) {
        return i/j;
    }

    @Override
    public int mul(int i, int j) {
        return i*j;
    }

    public void testFn(){
        System.out.println("xxx");
    }
}
