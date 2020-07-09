package dataStructure.hashTable;
/**
 * @ClassName MyHashTable
 * @Description 散列表 or 哈希表（数组+单链表）
 * @Author Josen
 * @Create 21:02 21:02
 */
public class MyHashTable {
    private SingleLinkedList[] hashTable;
    private int size;
    public MyHashTable(int size) {
        this.size = size;
        // 初始化哈希表结构
        hashTable = new SingleLinkedList[size];
        for (int i = 0; i < size; i++) {
            hashTable[i] = new SingleLinkedList();
        }
    }

    /**
     * 根据id找到指定雇员的链表位置以及雇员信息
     * @param id
     */
    public void findEmp(int id){
        int index = getIndex(id);
        Employee employee = hashTable[index].find(id);
        if(employee != null){
            System.out.printf("雇员信息：id=%d,name=%s \t 在第%d个LinkedList里\n",id,employee.name,index);
            return;
        }
        System.out.printf("没有id=%d的雇员信息\n",id);

    }

    /**
     * 删除指定id的雇员信息
     * @param id
     * @return
     */
    public void deleteEmp(int id){
        int index = getIndex(id);
        boolean res = hashTable[index].delete(id);
        if(res){
            System.out.printf("成功删除id=%d的雇员信息\n",id);
            return;
        }
        System.out.printf("没有找到id=%d的雇员\n",id);
    }
    /**
     * 添加雇员信息
     * @param emp
     */
    public void addEmp(Employee emp){
        int index = getIndex(emp.id);
        hashTable[index].add(emp);
    }

    /**
     * 打印hashTable[no]链表信息
     * @param no
     */
    public void showList(int no){
        int index = getIndex(no);
        hashTable[index].list(index);

    }

    /**
     * 散列函数
     * 作用：计算0-size的下标，存入对应的链表
     * @param num
     * @return
     */
    public int getIndex(int num){
        return num % size;
    }

    public int getSize() {
        return size;
    }
}
