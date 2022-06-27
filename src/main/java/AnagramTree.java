public class AnagramTree {
    public static boolean treeSolution(Node head) {
        final StringBuilder leftSb  = new StringBuilder();
        goLeft(head, leftSb, 0);
        final String left = leftSb.toString();

        final StringBuilder rightSb  = new StringBuilder();
        goRight(head, rightSb, 0);
        final String right = rightSb.toString();
        return left.equals(right);
    }

    private static void goLeft(Node node, StringBuilder stringBuilder, int level) {
        if (node == null){
            stringBuilder.append("None").append(level);
            return;
        }
        goLeft(node.left, stringBuilder, level + 1);
        stringBuilder.append(node.value).append(level);
        goLeft(node.right, stringBuilder, level + 1);
    }

    private static void goRight(Node node, StringBuilder stringBuilder, int level) {
        if (node == null){
            stringBuilder.append("None").append(level);
            return;
        }
        goRight(node.right, stringBuilder, level + 1);
        stringBuilder.append(node.value).append(level);
        goRight(node.left, stringBuilder, level + 1);
    }
}
