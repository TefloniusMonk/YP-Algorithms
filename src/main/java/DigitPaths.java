import java.util.*;

public class DigitPaths {
    public static int treeSolution(Node head){
        final List<String> lst = new LinkedList<>();
        collect(lst, head, "");
        return lst.stream().mapToInt(Integer::parseInt).sum();
    }

    public static void collect(List<String> lst, Node node, String prefix){
        if (node.right == null && node.left == null){
            lst.add(prefix + node.value);
        }
        if (node.left != null){
            collect(lst, node.left, prefix + node.value);
        }
        if (node.right != null){
            collect(lst, node.right, prefix + node.value);
        }
    }
}
