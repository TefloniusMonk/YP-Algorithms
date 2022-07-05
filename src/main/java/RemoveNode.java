/*
    Принцип работы.

    Находим нужный узел и его предка. Для найденного узла ищем самый правый элемент в левом поддереве и "вырезаем" его из дерева,
    если такого нет то ищем самый левый в правом поддереве и так же "вырезаем" его из дерева
    (У этих элементов не может быть двух предков, так что они удаляются так же, как и элементы из связного списка).
    Потом заменяем узел который надо удалить на найденный в поддереве.

    Временная сложность.

    O(H) - где H высота дерева. Для любого элемента мы сначала идем вглубь дерева чтобы его найти, а потом до листа, чтобы найти замену.
    Если элемента нет, то мы просто доходим до листа.

    Пространственная сложность.

    O(1) - Доп. память используется только для переменных
 */

// ID = 69286506
public class RemoveNode {
    public static Node remove(Node root, int key) {
        if (root == null || (isLeaf(root) && root.getValue() == key)) {
            return null;
        }
        Node node = root;
        Node prev = null;
        while (true) {
            if (node == null) {
                return root;
            }
            if (node.getValue() == key) {
                break;
            }
            prev = node;
            node = nextByKey(key, node);
        }
        if (isLeaf(node)) {
            replace(prev, node, null, false);
            return root;
        }
        Node replacementNode = findMaxRightInLeftBranch(node);
        if (replacementNode == null) {
            replacementNode = findMaxLeftInRightBranch(node);
        }

        if (node == root) {
            replacementNode.setLeft(node.getLeft());
            replacementNode.setRight(node.getRight());
            return replacementNode;
        }
        replace(prev, node, replacementNode, true);
        return root;
    }

    private static Node findMaxLeftInRightBranch(Node node) {
        if (node.getRight() == null) {
            return null;
        }
        Node prev = node;
        node = node.getRight();
        while (true) {
            if (node.getLeft() == null) {
                replace(prev, node, node.getRight(), false);
                return node;
            }
            prev = node;
            node = node.getLeft();
        }
    }

    private static Node findMaxRightInLeftBranch(Node node) {
        if (node.getLeft() == null) {
            return null;
        }
        Node prev = node;
        node = node.getLeft();
        while (true) {
            if (node.getRight() == null) {
                replace(prev, node, node.getLeft(), false);
                return node;
            }
            prev = node;
            node = node.getRight();
        }
    }


    private static void replace(Node prev, Node node, Node replace, boolean shouldMerge) {
        if (prev.getLeft() == node) {
            prev.setLeft(replace);
        } else {
            prev.setRight(replace);
        }
        if (shouldMerge && node.getRight() != null) {
            replace.setRight(node.getRight());
        }
        if (shouldMerge && node.getLeft() != null) {
            replace.setLeft(node.getLeft());
        }
    }

    private static boolean isLeaf(Node node) {
        return node.getLeft() == null && node.getRight() == null;
    }

    private static Node nextByKey(int key, Node node) {
        if (key < node.getValue()) {
            node = node.getLeft();
        } else {
            node = node.getRight();
        }
        return node;
    }
}
