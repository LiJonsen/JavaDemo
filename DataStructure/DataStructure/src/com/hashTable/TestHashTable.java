package dataStructure.hashTable;

import java.util.Scanner;

/**
 * @ClassName TestHashTable
 * @Description Details
 * @Author Josen
 * @Create 21:04 21:04
 */
public class TestHashTable {
    private static Scanner scan = new Scanner(System.in);
    private static MyHashTable hashTable = new MyHashTable(7);
    public static void main(String[] args) {
        while (true){
            System.out.println("add:添加雇员信息");
            System.out.println("list:打印雇员信息");
            System.out.println("find:查询雇员信息");
            System.out.println("delete:删除雇员信息");
            System.out.println("exit:退出");

            String str = scan.next();
            int id;
            switch (str){
                case "add":
                    System.out.println("雇员ID：");
                    id = scan.nextInt();
                    System.out.println("雇员姓名：");
                    String name = scan.next();
                    hashTable.addEmp(new Employee(id,name));
                    break;
                case "list":
                    System.out.println("打印哈希表下所有雇员信息：");
                    for (int i = 0; i < hashTable.getSize(); i++) {
                        hashTable.showList(i);
                    }
                    break;
                case "find":
                    System.out.println("输入要找的雇员ID：");
                    id = scan.nextInt();
                    hashTable.findEmp(id);
                    break;
                case "delete":
                    System.out.println("输入删除的雇员ID：");
                    id = scan.nextInt();
                    hashTable.deleteEmp(id);
                    break;
                case "exit":
                    System.exit(0);
                    break;
            }
        }
    }
}
