package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class BFS {
    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final var reader = new BufferedReader(new InputStreamReader(is))) {
            final var firstLine = reader.readLine().split(" ");
            final var vrtxs = Integer.parseInt(firstLine[0]);
            final var edges = Integer.parseInt(firstLine[1]);
            final var graph = new HashMap<Integer, List<Integer>>();
            var maxEdge = -1;
            for (int i = 0; i < edges; i++) {
                final var edge = Arrays.stream(reader.readLine().split(" "))
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);
                final var adjacent = graph.getOrDefault(edge[0], new ArrayList<>());
                adjacent.add(edge[1]);
                graph.put(edge[0], adjacent);

                final var adjacentInversed = graph.getOrDefault(edge[1], new ArrayList<>());
                adjacentInversed.add(edge[0]);
                graph.put(edge[1], adjacentInversed);

                maxEdge = Math.max(maxEdge, edge[0]);
            }
            final var startNode = Integer.parseInt(reader.readLine());
            final var builder = new StringBuilder();
            final var visited = new HashSet<Integer>();
            final Deque<Integer> queue = new LinkedList<>();
            queue.addLast(startNode);
            while (!queue.isEmpty()) {
                final var curNode = queue.pop();
                if (visited.contains(curNode)) {
                    continue;
                }
                builder.append(curNode).append(" ");
                visited.add(curNode);
                final var adjacent = graph.get(curNode);
                if (adjacent == null) {
                    continue;
                }
                adjacent.stream()
                        .sorted()
                        .forEach(queue::addLast);
            }
            return builder.toString().strip();
        }
    }
}
