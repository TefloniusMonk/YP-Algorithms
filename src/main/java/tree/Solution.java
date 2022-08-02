//import java.util.Optional;
//
//public class Solution {
//    public static tree.Node remove(tree.Node root, int key) {
//        if (root == null || (isLeaf(root) && root.getValue() == key)) {
//            return null;
//        }
//        tree.Node node = root;
//        tree.Node prev = null;
//        while (true) {
//            if (node == null) {
//                return root;
//            }
//            if (node.getValue() == key) {
//                break;
//            }
//            prev = node;
//            node = nextByKey(key, node);
//        }
//        if (isLeaf(node)) {
//            replace(prev, node, null, false);
//            return root;
//        }
//        final tree.Node finalNode = node;
//        final tree.Node replacementNode = Optional.ofNullable(findMaxRightInLeftBranch(node))
//                .orElseGet(() -> findMaxLeftInRightBranch(finalNode));
//
//        if (node == root) {
//            replacementNode.setLeft(node.getLeft());
//            replacementNode.setRight(node.getRight());
//            return replacementNode;
//        }
//        replace(prev, node, replacementNode, true);
//        return root;
//    }
//
//    private static tree.Node findMaxLeftInRightBranch(tree.Node node) {
//        if (node.getRight() == null) {
//            return null;
//        }
//        tree.Node prev = node;
//        node = node.getRight();
//        while (true) {
//            if (node.getLeft() == null) {
//                replace(prev, node, node.getRight(), false);
//                return node;
//            }
//            prev = node;
//            node = node.getLeft();
//        }
//    }
//
//    private static tree.Node findMaxRightInLeftBranch(tree.Node node) {
//        if (node.getLeft() == null) {
//            return null;
//        }
//        tree.Node prev = node;
//        node = node.getLeft();
//        while (true) {
//            if (node.getRight() == null) {
//                replace(prev, node, node.getLeft(), false);
//                return node;
//            }
//            prev = node;
//            node = node.getRight();
//        }
//    }
//
//
//    private static void replace(tree.Node prev, tree.Node node, tree.Node replace, boolean shouldMerge) {
//        if (prev.getLeft() == node) {
//            prev.setLeft(replace);
//            if (shouldMerge&& node.getLeft() != null){
//                replace.setLeft(node.getLeft());
//            }
//        } else {
//            prev.setRight(replace);
//            if (shouldMerge && node.getRight() != null) {
//                replace.setRight(node.getRight());
//            }
//            if (shouldMerge && node.getLeft() != null){
//                replace.setLeft(node.getLeft());
//            }
//        }
//    }
//
//    private static boolean isLeaf(tree.Node node) {
//        return node.getLeft() == null && node.getRight() == null;
//    }
//
//    private static tree.Node nextByKey(int key, tree.Node node) {
//        if (key < node.getValue()) {
//            node = node.getLeft();
//        } else {
//            node = node.getRight();
//        }
//        return node;
//    }
//}