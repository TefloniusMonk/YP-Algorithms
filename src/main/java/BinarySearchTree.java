public class BinarySearchTree {
    private Node root;

    public void add(int val) {
        Node node = root;
        while (true) {
            if (val < root.getValue()) {
                if (node.getLeft() == null) {
                    node.setLeft(new Node(null, null, val));
                    return;
                } else {
                    node = node.getLeft();
                }
            } else {
                if (node.getRight() == null) {
                    node.setRight(new Node(null, null, val));
                    return;
                } else {
                    node = node.getRight();
                }
            }
        }
    }
}
