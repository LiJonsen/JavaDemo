package JavaNewFeatures;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream API示例
 */
public class StreamApiTest {
    /**
     * 方式一：通过集合方式创建Stream
     */
    @Test
    public void testCollection(){
        List<String> list = new ArrayList<>();
        // 返回一个顺序流
        Stream<String> stream = list.stream();
        // 返回一个并行流
        Stream<String> stringStream = list.parallelStream();
    }
    /**
     * 方式二：通过数组方式创建Stream
     */
    @Test
    public void testArray(){
        String[] strs = {"a","b","c"};
        Stream<String> stream = Arrays.stream(strs);
        System.out.println(stream);
    }
    /**
     * 方式三：通过Stream的of()方法
     */
    @Test
    public void testStreamOf(){
        Stream<String> a = Stream.of("a", "b", "C");
    }
    /**
     * 方式四：创建无限流
     */
    @Test
    public void testStream(){
        // 迭代
        // 遍历前10个偶数
        Stream.iterate(0,t->t+2).limit(10).forEach(System.out::println);

        // 生成
        // 输出10个随机数
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

    /**
     * 中间操作-筛选与切片
     */
    @Test
    public void testOperating(){
        List<Employee> employees = StreamApiTest.getEmployees();
        // 1. filter(Predicate p) - 接收Lambda，从流中排出某些指定条件元素
        // 过滤salary>=5000
        Stream<Employee> employeeStream = employees.stream().filter(e -> e.getSalary() >= 5000);
        employeeStream.forEach(System.out::println);
        System.out.println();
        // 2. limit(int n) - 截断流，使其元素不超过给定数量
        employees.stream().limit(2).forEach(System.out::println);
        System.out.println();

        // 3. skip(int n) - 从0开始跳过n个元素，返回n后面所有元素
        employees.stream().skip(3).forEach(System.out::println);
        System.out.println();

        // 4. distinct() - 筛选，通过流所生成元素的 hashCode() 和 equals() 去重
        employees.add(new Employee("刘德华",30,8000,1001));
        employees.add(new Employee("刘德华",30,8200,1001));
        employees.add(new Employee("刘德华",30,8000,1001));

        employees.stream().distinct().forEach(System.out::println);
    }
    /**
     * 中间操作-映射
     */
    @Test
    public void testMap(){
        List<Employee> employees = StreamApiTest.getEmployees();

        // 练习1：获取name包含“周”的名字（不包含其他属性）
        Stream<String> stringStream = employees.stream().map(val -> val.getName());
        stringStream.filter(name->name.contains("周")).forEach(System.out::println);
        System.out.println();
        // 练习2：将二元集合展开成一元集合  String[char[]]
        List<String> list = Arrays.asList("aa","bb","CC","DD");
        Stream stream = list.stream().flatMap(StreamApiTest::fromStringToStream);
        stream.forEach(System.out::println);
    }

    /**
     * 中间操作-排序
     */
    @Test
    public void testSortOperating(){
        List<Employee> employees = StreamApiTest.getEmployees();
        // 1. 默认使用元素对应类中的Comparable自然排序接口，没有实现则抛出ClassCastException异常(排序age)
        employees.stream().sorted().forEach(System.out::println);
        System.out.println();
        // 2. 使用指定的定制排序Comparator(排序salary)
        employees.stream().sorted((o1,o2)->Double.compare(o1.getSalary(),o2.getSalary())).forEach(System.out::println);
    }

    /**
     * 终止操作 - 查找与匹配
     */
    @Test
    public void testFindAndMatch(){
        List<Employee> employees = StreamApiTest.getEmployees();

        // allMatch(Predicate p) - Predicate条件是否匹配所有元素
        // employees中age是否都大于18
        boolean b = employees.stream().allMatch(e -> e.getAge() > 18);
        System.out.println(b); // true

        // anyMatch(Predicate p) - 是否匹配至少一个元素
        // employees中age是否都小于18
        boolean b2 = employees.stream().anyMatch(e -> e.getAge() < 18);
        System.out.println(b2); // false

        // noneMatch(Predicate p) - 检查是否没有匹配的元素
        boolean b3 = employees.stream().noneMatch(e -> e.getAge() < 18);
        System.out.println(b3); // true

        // findFirst() - 返回流中的第一个元素
        Optional<Employee> first = employees.stream().findFirst();
        System.out.println(first);

        // findAny() - 返回流中的一个任意元素
        Optional<Employee> ele = employees.stream().findAny();
        System.out.println(ele);

        // max() - 返回流中的最大值
        Optional<Double> max = employees.stream().map(val -> val.getSalary()).max(Double::compare);
        System.out.println(max);

        // min() - 返回流中的最小值
        Optional<Double> min = employees.stream().map(val -> val.getSalary()).min(Double::compare);
        System.out.println(min);
        // forEach() - 内部迭代
    }
    /**
     * 终止操作 - 归约
     */
    @Test
    public void testReduce(){
        List<Employee> employees = StreamApiTest.getEmployees();
        // reduce(BinaryOperator) - 计算employees所有salary的总和
        Optional<Double> salaryCount = employees.stream().map(val -> val.getSalary()).reduce((s1, s2) -> s1 + s2);
        System.out.println(salaryCount);

        // reduce(T identity, BinaryOperator) - - 计算employees所有salary的总和 + 1000
        double more = 1000;
        Double salaryCount2 = employees.stream().map(val -> val.getSalary()).reduce(more,Double::sum);
        System.out.println(salaryCount2);
    }
    /**
     * 终止操作 - 收集
     */
    @Test
    public void testCollect(){
        List<Employee> employees = StreamApiTest.getEmployees();
        // 查询salary>6000的employee，结果返回一个List或Set
        List<Employee> collect = employees.stream().filter(val->val.getSalary()>8000).collect(Collectors.toList());
        for(Employee obj : collect){
            System.out.println(obj);
        }


    }

    // 将字符串转换成char集合的Stream
    public static Stream<Character> fromStringToStream(String str){
        List<Character> list = new ArrayList<>();
        for(Character c : str.toCharArray()){
            list.add(c);
        }
        return list.stream();
    }

    public static List<Employee> getEmployees(){
         List list = new ArrayList<>();
         list.add(new Employee("刘德华",30,8000,1001));
         list.add(new Employee("张学友",25,5000,1002));
         list.add(new Employee("周润发",55,12000,1003));
         list.add(new Employee("周星驰",29,9999,1004));
         list.add(new Employee("梁朝伟",23,4500,1005));
         return list;
    }
}
