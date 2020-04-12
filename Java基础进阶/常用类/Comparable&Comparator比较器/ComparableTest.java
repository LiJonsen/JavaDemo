package com.atguigu.common_class;
import org.junit.Test;
import java.util.Arrays;
/**
 * 测试Comparable自然排序
 * 属于通用形，一般用于比较对象某个属性大小、高低
 */
public class ComparableTest {
    @Test
    public void testing(){
        Goods goods1 = new Goods("MiTV",98);
        Goods goods2 = new Goods("HuaWeiTV",65);
        Goods goods3 = new Goods("AiTV",54);
        Goods goods4 = new Goods("MdTV",120);
        Goods goods5 = new Goods("HsTV",119);
        Goods goods6 = new Goods("BiTV",65);
        Goods[] arr = new Goods[]{goods1,goods2,goods3,goods4,goods5,goods6};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
