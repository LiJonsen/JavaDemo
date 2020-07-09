package dataStructure.binaryTree;

/**
 * @ClassName TestBinaryTree
 * @Description
 *                   id:1,name:James
 *    id:2,name:Curry                 id:3,name:Durant
 *                        id:5,name:Kerry             id:4,name:PG
 * @Author Josen
 * @Create 15:30 15:30
 */
public class TestBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, "James");
        TreeNode node2 = new TreeNode(2, "Curry");
        TreeNode node3 = new TreeNode(3, "Durant");
        TreeNode node4 = new TreeNode(4, "PG");
        TreeNode node5 = new TreeNode(5, "Kerry");
        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);
        BinaryTree binaryTree = new BinaryTree(root);

//        binaryTree.preOrder();
//        binaryTree.searchTreeNode(5);
        boolean b = binaryTree.preOrderForDelete(5);
        System.out.println(b?"Delete success":"Delete fail");
        binaryTree.preOrder();

    }
}
