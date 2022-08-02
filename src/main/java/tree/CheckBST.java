package tree;

public class CheckBST {
    public static boolean treeSolution(Node head) {
        return isBST(head);
    }

    public static boolean isBST(Node node) {
        if (node == null) {
            return true;
        }
        if (node.left != null && node.left.value >= node.value) {
            return false;
        }
        if (node.right != null && node.right.value <= node.value) {
            return false;
        }
        return isBST(node.left) && isBST(node.right);
    }
}
