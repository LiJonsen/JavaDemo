package CGlibDecimalProxy;

/**
 * @ClassName Employee
 * @Description Details
 * @Author Josen
 * @Create 2020/7/25 23:00
 */
public class Employee {
    private String name;
    private Integer age;

    public Employee() {
    }
    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void sayHello(){
        System.out.printf("Hello! My name is %s , I am %d years old...\n",name,age);
    }
    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
