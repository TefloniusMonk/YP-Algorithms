public class TwinTrees {
    public static boolean treeSolution(Node head1, Node head2){
        final StringBuilder sb1  = new StringBuilder();
        goLeft(head1, sb1, 0);
        final String left1 = sb1.toString();

        final StringBuilder sb2  = new StringBuilder();
        goLeft(head2, sb2, 0);
        final String left2 = sb2.toString();
        return left1.equals(left2);
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
}
