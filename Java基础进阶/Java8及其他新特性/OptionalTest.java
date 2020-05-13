package JavaNewFeatures;
import java.util.Optional;

/**
 * Optional类示例
 * 作用：避免空指针异常
 */
public class OptionalTest {
    public static void main(String[] args) {
        Girl rose = new Girl("Rose");
        Boy jack = new Boy("Jack", rose);
        String boyWifeName = OptionalTest.getBoyWifeName(jack);
        System.out.println(boyWifeName);
    }

    public static String getBoyWifeName(Boy boy){
        Optional<Boy> o_boy = Optional.ofNullable(boy);
        // 设置当boy为null时，设置默认值
        Boy boy1 = o_boy.orElse(new Boy("Tom", new Girl("Mary")));
        Girl wife = boy1.getWife();
        // 当wife为null时，设置默认值
        Optional<Girl> o_girl = Optional.ofNullable(wife);
        String name = o_girl.orElse(new Girl("Jina")).getName();
        return name;
    }
}


class Boy{
    private String name;
    private Girl wife;
    public Boy(String jack){}
    public Boy(String name, Girl wife) {
        this.name = name;
        this.wife = wife;
    }
    public String getName() {
        return name;
    }

    public Girl getWife() {
        return wife;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "name='" + name + '\'' +
                ", wife='" + wife + '\'' +
                '}';
    }
}
class Girl{
    private String name;
    public Girl(){}
    public Girl(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Girl{" +
                "name='" + name + '\'' +
                '}';
    }
}