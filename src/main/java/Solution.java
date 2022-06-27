//
//public class Solution {
//    public static int treeSolution(Node head) {
//        return Math.max(maxDepth(head.left, 1), maxDepth(head.right, 1));
//    }
//
//    public static int maxDepth(Node node, int curMax) {
//        if (node == null) {
//            return curMax;
//        }
//        return Math.max(maxDepth(node.left, curMax + 1), maxDepth(node.right, curMax));
//    }
//}
//
//class Node {
//    int value;
//    Node left;
//    Node right;
//
//    Node(int value) {
//        this.value = value;
//        right = null;
//        left = null;
//    }
//}