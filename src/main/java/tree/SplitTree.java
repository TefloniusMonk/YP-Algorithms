package tree;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.*;

public class SplitTree {
    public static List<Node> split(Node root, int k) {
        final Node[] result = new Node[2];
        System.out.printf("Root=%s, left=%s, right=%s, k=%s", string(root), string(root.getLeft()), string(root.getRight()), k);
        if (sizeOf(root.getLeft()) + 1 == k) {
            result[0] = root;
            result[1] = root.getRight();
            if (result[1] != null) {
                result[1].setSize(root.getSize() - k);
            }
            root.setSize(k);
            root.setRight(null);
        } else {
            if (root.getLeft() != null && root.getLeft().getSize() >= k) {
                searchLeft(root, k, result);
            } else {
                searchRight(root, k, result);
            }

        }
        return Arrays.asList(result);
    }

    private static String string(Node node) {
        return node == null ? "null" : String.format("{size=%s,val=%s,leftSize=%s,rightSize=%s}",
                node.getSize(), node.getValue(), sizeOf(node.getLeft()), sizeOf(node.getRight()));
    }

    private static void searchLeft(Node root, int k, Node[] result) {
        result[1] = root;
        final List<Node> split = split(root.getLeft(), k);
        setLeft(root, split.get(1));
        result[0] = split.get(0);
    }

    private static void setLeft(Node root, Node node) {
        root.setSize(sizeOf(root.getRight()) + 1);
        root.setLeft(node);
        root.setSize(root.getSize() + sizeOf(node));
    }

    private static void searchRight(Node root, int k, Node[] result) {
        result[0] = root;
        final List<Node> split = split(root.getRight(), k - sizeOf(root.getLeft()) - 1);
        setRight(root, split.get(0));
        result[1] = split.get(1);
    }

    private static void setRight(Node root, Node node) {
        root.setSize(sizeOf(root.getLeft()) + 1);
        root.setRight(node);
        root.setSize(root.getSize() + sizeOf(node));
    }

    private static int sizeOf(Node node) {
        return node == null ? 0 : node.getSize();
    }

    public static class Node {
        private Node left;
        private Node right;
        private int value;
        private int size;

        public Node(Node left, Node right, int value, int size) {
            this.left = left;
            this.right = right;
            this.value = value;
            this.size = size;
        }

        private static int sizeOf(Node node) {
            return node == null ? 0 : node.getSize();
        }

        @JsonCreator
        public Node() {
        }

        public int getValue() {
            return value;
        }

        public Node getRight() {
            return right;
        }

        private void setRight(Node right) {
            this.right = right;
        }

        public Node getLeft() {
            return left;
        }

        private void setLeft(Node left) {
            this.left = left;
        }

        private void setValue(int value) {
            this.value = value;
        }

        public int getSize() {
            return size;
        }

        private void setSize(int size) {
            this.size = size;
        }
    }
}
