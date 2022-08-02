package tree;

import tree.Node;

public class MaxDepth {
    public static int treeSolution(Node head) {
        return Math.max(maxDepth(head.left, 1), maxDepth(head.right, 1));
    }

    public static int maxDepth(Node node, int curMax) {
        if (node == null) {
            return curMax;
        }
        return Math.max(maxDepth(node.left, curMax + 1), maxDepth(node.right, curMax + 1));
    }
}
