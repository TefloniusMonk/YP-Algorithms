package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Bipartite {
    private static Set<Integer> blueSet;
    private static Set<Integer> redSet;
    private static final String NO = "NO";
    private static final String YES = "YES";

    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        blueSet = new HashSet<>();
        redSet = new HashSet<>();
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final String[] firstLine = reader.readLine().split(" ");
            final int vertexes = Integer.parseInt(firstLine[0]);
            if (vertexes == 1) {
                return YES;
            }
            final int edgesCount = Integer.parseInt(firstLine[1]);
            final HashMap<Integer, Set<Integer>> graph = new HashMap<>();
            for (int i = 0; i < edgesCount; i++) {
                final Integer[] edge = Arrays.stream(reader.readLine().split(" "))
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);
                if (!Objects.equals(edge[0], edge[1])) {
                    final Set<Integer> adjacent = graph.getOrDefault(edge[0], new HashSet<>());
                    adjacent.add(edge[1]);
                    graph.put(edge[0], adjacent);

                    final Set<Integer> adjacentInversed = graph.getOrDefault(edge[1], new HashSet<>());
                    adjacentInversed.add(edge[0]);
                    graph.put(edge[1], adjacentInversed);
                }
            }
            for (int i = 1; i <= vertexes; i++) {
                if (isPainted(i)) {
                    continue;
                }
                final Deque<Node> queue = new LinkedList<>();
                queue.addFirst(Node.paint(i, Color.BLUE));
                while (!queue.isEmpty()) {
                    final Node curNode = queue.pop();
                    final Set<Integer> adjacent = graph.get(curNode.val);
                    if (adjacent == null || adjacent.isEmpty()) {
                        continue;
                    }
                    for (Integer nextNode : adjacent) {
                        if (isPainted(nextNode)) {
                            if (sameColor(curNode, nextNode)) {
                                return NO;
                            }
                            continue;
                        }
                        queue.addLast(Node.paint(nextNode, nextColor(curNode.color)));
                    }
                }
            }
            return YES;
        }
    }

    private static Color nextColor(Color color) {
        return color == Color.BLUE ? Color.RED : Color.BLUE;
    }

    private static boolean sameColor(Node curNode, Integer nextNode) {
        if (curNode.color == Color.BLUE) {
            return blueSet.contains(nextNode);
        }
        return redSet.contains(nextNode);
    }

    private static boolean isPainted(int i) {
        return blueSet.contains(i) || redSet.contains(i);
    }

    static class Node {
        public final int val;
        public final Color color;

        private Node(int val, Color color) {
            this.val = val;
            this.color = color;
            if (color == Color.BLUE) {
                blueSet.add(val);
            } else {
                redSet.add(val);
            }
        }

        public static Node paint(int val, Color color) {
            return new Node(val, color);
        }


    }

    enum Color {
        RED, BLUE
    }
}
