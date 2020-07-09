package dataStructure.binaryTree;

/**
 * @ClassName BinaryTree
 * @Description
 * 实现前序遍历、查找、删除
 * @Author Josen
 * @Create 15:28 15:28
 */
public class BinaryTree {
    // root根节点
    private TreeNode root;
    public BinaryTree(TreeNode root) {
        this.root = root;
    }
    /**
     * 前序删除
     */
    public boolean preOrderForDelete(int no){
        if(root==null){
            printEmptyTip();
            return false;
        }
        if(root.getId() == no){
            root = null;
            System.out.println("Tip:no=root.no, delete all tree node...");
            return true;
        }

        return root.preOrderDelete(no);
    }
    /**
     * 前序、中序、后续遍历
     */
    public void preOrder(){
        if(root == null){
            printEmptyTip();
            return;
        }
        System.out.println("************前序遍历**************");
        root.preOrder();
//        System.out.println("************中序遍历**************");
//        root.infixOrder();
//        System.out.println("************后序遍历**************");
//        root.postOrder();
    }

    /**
     * 查询树节点
     * @param no
     */
    public void searchTreeNode(int no){
        if(root == null){
            printEmptyTip();
            return;
        }
        TreeNode treeNode = root.preOrderSearch(no);
        if(treeNode == null){
            System.out.printf("Can't find TreeNode.no=%d...\n",no);
            return;
        }

        System.out.printf("Find TreeNode result：no=%d,name=%s\n",treeNode.getId(),treeNode.getName());
    }

    // 二叉树为空提示
    private void printEmptyTip(){
        System.out.println("Binary tree is empty");
    }

}
