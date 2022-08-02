package tree;

public class MaxTreePath {
    private static int max = Integer.MIN_VALUE;

    public static int treeSolution(Node head) {
        findMax(head);
        return max;
    }

    public static int findMax(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            max = Math.max(max, node.value);
            return node.value;
        }
        final int leftSum = findMax(node.left);
        final int rightSum = findMax(node.right);
        final int maxSubtree = Math.max(leftSum, rightSum);
        final int localMax = Math.max(maxSubtree + node.value, node.value);
        max = Math.max(max, Math.max(localMax, node.value + leftSum + rightSum));
        return localMax;
    }
}
