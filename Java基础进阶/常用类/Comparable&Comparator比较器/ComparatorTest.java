package com.atguigu.common_class;
import org.junit.Test;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 测试Comparator定制排序
 * 定制某个对象特有的排序方式
 */
public class ComparatorTest {
    @Test
    public void testing(){
        Goods goods1 = new Goods("MiTV",98);
        Goods goods2 = new Goods("HuaWeiTV",65);
        Goods goods3 = new Goods("AiTV",54);
        Goods goods4 = new Goods("MdTV",120);
        Goods goods5 = new Goods("HsTV",119);
        Goods goods6 = new Goods("BiTV",65);
        Goods[] arr = new Goods[]{goods1,goods2,goods3,goods4,goods5,goods6};

        Arrays.sort(arr, new Comparator<Goods>() {
            @Override
            public int compare(Goods o1, Goods o2) {
                if(o1 instanceof Goods && o2 instanceof Goods){
                    Goods g1 = (Goods) o1;
                    Goods g2 = (Goods) o2;
                    // 价格一样，继续比较name属性
                    if(g1.getPrice() == g2.getPrice()){
                        // 字母从小到大
                        return g1.getName().compareTo(g2.getName());
                    }
                    // 价格从低到高
                    return Double.compare(g1.getPrice(),g2.getPrice());
                }
                throw new RuntimeException("传入参数类型必须是Goods");
            }
        });
        System.out.println(Arrays.toString(arr));
    }
}
