package Reflection;

public class Person {
    private String name;
    private int age;
    public Double money;
    Person self;
    public Person(){}
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    @MyAnnotation
    public void show(){
        System.out.println("调用Person类的show()方法");
    }
    @MyAnnotation("Yoo Bro!")
    private String getDesc(String val,boolean type, int num) throws NullPointerException,ClassCastException{
        return "getDesc()返回值";
    }
    @Override
    public String toString() {
        return "Person{" +
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
