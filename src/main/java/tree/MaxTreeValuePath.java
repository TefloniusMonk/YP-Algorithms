package tree;

public class MaxTreeValuePath {
    public static int treeSolution(Node head) {
        return maxDepth(head, 0);
    }

    public static int maxDepth(Node node, int curMax) {
        if (node == null) {
            return curMax;
        }
        return Math.max(maxDepth(node, node.value),
                Math.max(maxDepth(node.left, curMax + node.value), maxDepth(node.right, curMax + node.value)));
    }
}
