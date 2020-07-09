package dataStructure.hashTable;

/**
 * @ClassName SingleLinkedList
 * @Description 单链表类，用于存放雇员信息
 * @Author Josen
 * @Create 21:02 21:02
 */
public class SingleLinkedList {
    public Employee head;

    /**
     * 删除指定id的节点
     * @param id
     * @return
     */
    public boolean delete(int id){
        // 如果id等于head节点
        if(head.id == id){
            if(head.next == null){
                head = null;
            }else{
               head = head.next;
            }
            return true;
        }
        Employee cur = head;
        while (true){
            if(cur.next == null){
                return false;
            }
            if(cur.next.id == id){
                // 修改指针指向
                if(cur.next.next != null){
                    cur.next = cur.next.next;
                }else{
                    cur.next = null;
                }
                return true;
            }
            cur = cur.next;
        }
    }
    /**
     * 根据id查询指定雇员
     * @param id
     */
    public Employee find(int id){
        Employee cur = head;
        while (true){
            if(cur == null){
                return null;
            }
            if(cur.id == id){
                return cur;
            }
            cur = cur.next;
        }
    }
    /**
     * 添加雇员信息
     * @param emp
     */
    public void add(Employee emp){
        if(head == null){
            head = emp;
            return;
        }
        Employee cur = head;

        while (true){
            if(cur.next == null){
                cur.next = emp;
                return;
            }
            cur = cur.next;
        }
    }

    /**
     * 打印哈希表数组下标=no的链表雇员信息
     * @param no
     */
    public void list(int no){
        if(head == null){
            System.out.printf("第%d个SingleLinkedList为空\n",no);
            return;
        }
        Employee cur = head;

        System.out.printf("第%d个SingleLinkedList雇员信息：",no);

        while (true){
            if(cur == null)
                break;
            System.out.printf(" -> id=%d,name=%s \t",cur.id,cur.name);
            cur = cur.next;
        }

        System.out.println("");
    }

}
