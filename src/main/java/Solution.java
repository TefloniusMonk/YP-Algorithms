import java.util.LinkedList;
import java.util.Stack;

public class Solution {
    public static int treeSolution(Node head) {
        final Stack<Node> stack = new Stack<>();
        stack.push(head);
        int max = head.value;
        do {
            final Node node = stack.pop();
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            max = Math.max(max, node.value);
        } while (!stack.isEmpty());
        return max;
    }
}

class Node {
    int value;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
        right = null;
        left = null;
    }
}