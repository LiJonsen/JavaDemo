package Reflection;

public class Person extends Creature<String>{
    private String name;
    private int age;
    public Double money;
    Person self;
    public Person(){}
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    private Person(String name){
        this.name = name;
    }
    @MyAnnotation
    public void show(){
        System.out.println("调用Person类的show()方法");
    }
    @MyAnnotation("Yoo Bro!")
    private String getDesc(String val,boolean type, int num) throws NullPointerException,ClassCastException{
        System.out.println("调用getDesc()："+val + type + num);
        return "getDesc()返回值";
    }
    public static void commonPrint(){
        System.out.println("调用Person类静态方法");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", money=" + money +
                ", self=" + self +
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
