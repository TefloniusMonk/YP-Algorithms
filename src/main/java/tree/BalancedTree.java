package tree;

public class BalancedTree {
    public static int minDepth(Node node, int curMax) {
        if (node == null) {
            return curMax;
        }
        return Math.min(minDepth(node.left, curMax + 1), minDepth(node.right, curMax + 1));
    }

    public static int maxDepth(Node node, int curMax) {
        if (node == null) {
            return curMax;
        }
        return Math.max(maxDepth(node.left, curMax + 1), maxDepth(node.right, curMax + 1));
    }

    public static boolean treeSolution(Node head) {
        final int maxDepth = maxDepth(head, 1);
        final int minDepth = minDepth(head, 1);
        return minDepth + 1 >= maxDepth;
    }
}
