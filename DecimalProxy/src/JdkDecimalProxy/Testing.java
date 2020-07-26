package JdkDecimalProxy;

import JdkDecimalProxy.beans.CalculatorImpl;
import JdkDecimalProxy.intefaces.Calculator;

import java.util.Properties;


/**
 * @ClassName Testing
 * @Description Details
 * @Author Josen
 * @Create 2020/7/25 20:03
 */
public class Testing {
    public static void main(String[] args) {

        Properties properties = System.getProperties();
        properties.put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Calculator calculator = new CalculatorImpl();
        Calculator proxy = DecimalProxy.getProxyByInterface(calculator);
    }
}
