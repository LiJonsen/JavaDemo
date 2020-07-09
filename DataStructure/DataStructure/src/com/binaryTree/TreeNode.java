package dataStructure.binaryTree;

/**
 * @ClassName TreeNode
 * @Description 树节点
 * @Author Josen
 * @Create 15:28 15:28
 */
public class TreeNode {
    private int id;
    private String name;
    private TreeNode left;// 左子节点
    private TreeNode right;// 右子节点

    public TreeNode(int id, String name) {
        this.id = id;
        this.name = name;
    }
    /**
     * 前序删除
     */
    public boolean preOrderDelete(int no){
        boolean status = false;
        // 1. 对比左/右子节点，找到则=null
        if(this.left!=null && this.left.getId() == no){
            this.left = null;
            return true;
        }
        if(this.right!=null && this.right.getId() == no){
            this.right = null;
            return true;
        }

        // 2. 递归继续寻找对应的no
        if(this.left!=null){
            status = this.left.preOrderDelete(no);
        }

        if(this.right != null){
            status = this.right.preOrderDelete(no);
        }

        return status;
    }
    /**
     * 前序查找
     */
    public TreeNode preOrderSearch(int no){
        if(this.id == no){
            return this;
        }
        TreeNode res = null;
        if(this.left != null){
            res = this.left.preOrderSearch(no);
        }
        if(res!=null)
            return res;

        if(this.right != null){
            res = this.right.preOrderSearch(no);
        }
        return res;
    }

    /**
     * 前序遍历
     * 特点：先输出父节点，再遍历左子树和右子树
     */
    public void preOrder(){
        // 1. 先输出父节点
        System.out.println(this);

        // 2. 遍历左子树
        if(this.left != null){
            this.left.preOrder();
        }
        // 3. 遍历右子树
        if(this.right != null){
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历
     * 特点：先遍历左子树，再输出父节点，最后遍历右子树
     */
    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.infixOrder();
        }
    }

    /**
     * 后续遍历
     * 特点：先遍历左子树，再遍历右子树，最后输出父节点
     */
    public void postOrder(){
        if(this.left!=null)
            this.left.postOrder();

        if(this.right!=null)
            this.right.postOrder();

        System.out.println(this);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
