package dataStructure.hashTable;

/**
 * @ClassName Employee
 * @Description 雇员节点
 * @Author Josen
 * @Create 21:03 21:03
 */
public class Employee {
    public String name;
    public int id;
    public Employee next;

    public Employee(int id,String name) {
        this.name = name;
        this.id = id;
    }
}
