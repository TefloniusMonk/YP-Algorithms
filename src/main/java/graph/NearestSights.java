package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class NearestSights {

    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final String[] firstLine = reader.readLine().split(" ");
            final int vertexes = Integer.parseInt(firstLine[0]);
            final int edgesCount = Integer.parseInt(firstLine[1]);
            final HashMap<Integer, Set<Edge>> graph = new HashMap<>();
            for (int i = 1; i <= vertexes; i++) {
                graph.put(i, new HashSet<>());
            }
            for (int i = 0; i < edgesCount; i++) {
                final Integer[] edge = Arrays.stream(reader.readLine().split(" "))
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);
                if (!Objects.equals(edge[0], edge[1])) {

                    final Set<Edge> adjacent = graph.getOrDefault(edge[0], new HashSet<>());
                    adjacent.add(new Edge(edge[1], edge[2]));
                    graph.put(edge[0], adjacent);

                    final Set<Edge> adjacentInversed = graph.getOrDefault(edge[1], new HashSet<>());
                    adjacentInversed.add(new Edge(edge[0], edge[2]));

                    graph.put(edge[1], adjacentInversed);
                }
            }
            final int[][] result = new int[vertexes + 1][vertexes + 1];
            for (int i = 1; i <= vertexes; i++) {
                result[i] = dijkstra(graph, i);
            }
            for (int i = 0; i <= vertexes; i++) {
                for (int j = 1; j < i; j++) {
                    result[i][j] = result[j][i];
                }
            }
            return Arrays.stream(result)
                    .map(arr -> Arrays.stream(arr)
                            .mapToObj(i -> {
                                if (i == Integer.MAX_VALUE) {
                                    return "-1";
                                }
                                return String.valueOf(i);
                            })
                            .skip(1)
                            .collect(Collectors.joining(" ")))
                    .skip(1)
                    .collect(Collectors.joining("\n"));
        }
    }

    private static int[] dijkstra(HashMap<Integer, Set<Edge>> graph, int startNode) {
        final Set<Integer> visited = new HashSet<>(graph.size());
        final int[] dist = new int[graph.size() + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[startNode] = 0;
        while (visited.size() != graph.size()) {
            final var curVert = findMinDistOfNotVisitedVertex(graph, visited, dist);
            if (curVert == -1) {
                break;
            }
            visited.add(curVert);
            final var neighbours = graph.getOrDefault(curVert, new HashSet<>());
            for (var edge : neighbours) {
                relax(curVert, edge, dist);
            }
        }
        return dist;
    }

    private static void relax(int curVert, Edge edge, int[] dist) {
        if (dist[edge.to] > dist[curVert] + edge.weight) {
            dist[edge.to] = dist[curVert] + edge.weight;
        }
    }

    private static int findMinDistOfNotVisitedVertex(HashMap<Integer, Set<Edge>> graph, Set<Integer> visited, int[] dist) {
        var curMin = Integer.MAX_VALUE;
        var curMinVert = -1;
        for (int i = 1; i < graph.size() + 1; i++) {
            if (!visited.contains(i) && dist[i] < curMin) {
                curMin = dist[i];
                curMinVert = i;
            }
        }
        return curMinVert;
    }

    static class Edge {
        public final int to;
        public final int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            final Edge edge = (Edge) o;

            if (to != edge.to) {
                return false;
            }
            return weight == edge.weight;
        }

        @Override
        public int hashCode() {
            int result = to;
            result = 31 * result + weight;
            return result;
        }
    }
}
