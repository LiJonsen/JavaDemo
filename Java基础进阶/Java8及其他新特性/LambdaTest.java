package JavaNewFeatures;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.*;

public class LambdaTest {
    @Test
    public void test(){
        // 格式一：形参根据泛型进行类型推断
        Comparator<Integer> com1 = (o1,o2)->o1.compareTo(o2);
        // 格式二：当方法体只有一行代码时，可省略{}；
        //         如果有返回值，return 可省略；
        Runnable r1 = () -> System.out.println("Do Something");

        // 格式三：当形参列表只有一个时，可省略()
        Consumer<String> c1 = val -> System.out.println(val);

        // 格式四：若方法体有多行代码，使用{}包裹
        Consumer<Integer> c2 = num -> {
            System.out.println("第一行");
            System.out.println("第二行："+num);
        };
    }
    @Test
    public void testConsumer(){
        LambdaTest.UseConsumer("Hello Consumer",(val)-> System.out.println("打印Val："+val));
    }
    public static void UseConsumer(String val,Consumer<String> con){
        con.accept(val);
    }

    /**
     * 使用Predicate判定型函数
     * 判断val是否存在Hello字符串
     */
    @Test
    public void testPredicate(){
        boolean status = LambdaTest.UsePredicate("Hello Predicate", (val) -> val.contains("Hello"));
        System.out.println(status);// true
    }
    public static boolean UsePredicate(String val, Predicate<String> pre){
        return pre.test(val);
    }
    @Test
    public void test3(){
        Comparator<Integer> com = Integer::compare;
        int compare = com.compare(33, 12);
        System.out.println(compare);
    }
    @Test
    public void test4(){
        // 完整写法
        Function<Double,Long> fun1 = new Function<Double, Long>() {
            @Override
            public Long apply(Double val) {
                return Math.round(val);
            }
        };
        System.out.println(fun1.apply(23.5));
        // Lambda表达式写法
        Function<Double,Long> fn = (val) -> Math.round(val);
        System.out.println(fn.apply(33.7));

        // 方法引用写法
        Function<Double,Long> fun3 = Math::round;
        System.out.println(fun3.apply(77.5));
    }
    @Test
    public void test5(){
        BiPredicate<String,String> bpre = String::equals;
        System.out.println(bpre.test("abc", "abc"));
    }

    /**
     * 构造器引用
     */
    @Test
    public void testConstructor(){
        // 1. 完整写法
        Supplier<Person> sup = new Supplier<Person>() {
            @Override
            public Person get() {
                return new Person("KK");
            }
        };
        System.out.println(sup.get());
        // 2. Lambda表达式写法
        Supplier<Person> sup2 = ()->new Person("Jack");
        System.out.println(sup2.get());
        // 3. 构造器引用写法
        // 3.1 调用空参构造器
        Supplier<Person> sup3 = Person::new;
        System.out.println(sup3.get());
        // 3.2 调用带1个参数的构造器
        Function<String,Person> fn = Person::new;
        System.out.println(fn.apply("Jerry"));
        // 3.3 调用带2个参数的构造器
        BiFunction<String,Integer,Person> bifun  = Person::new;
        System.out.println(bifun.apply("Tom", 20));

    }
    /**
     * 数组引用
     */
    @Test
    public void testArray(){
        // 1. 使用Lambda表达式
        Function<Integer,String[]> fun = (len) -> new String[len];
        String[] apply = fun.apply(5);
        System.out.println(Arrays.toString(apply));

        // 2. 使用数组引用
        Function<Integer,String[]> fun2 = String[]::new;
        String[] apply1 = fun2.apply(10);
        System.out.println(Arrays.toString(apply1));
    }
}

class Person{
    private String name;
    private int age;
    public Person(){}
    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}