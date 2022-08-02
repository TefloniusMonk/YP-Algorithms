package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class FullGraph {
    private static final String NO = "NO";
    private static final String YES = "YES";

    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
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
                final Set<Integer> edges = graph.get(i);
                if (edges == null || edges.size() != vertexes - 1) {
                    return NO;
                }
            }
            return YES;
        }
    }

}
