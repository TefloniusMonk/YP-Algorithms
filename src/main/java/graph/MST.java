package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.String.format;

public class MST {
    private static final String OOPS = "Oops! I did it again";

    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final String[] firstLine = reader.readLine().split(" ");
            final int vertexes = Integer.parseInt(firstLine[0]);
            if (vertexes == 1) {
                return "0";
            }
            final int edgesCount = Integer.parseInt(firstLine[1]);
            final Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
            for (int i = 0; i < edgesCount; i++) {
                final Integer[] edge = Arrays.stream(reader.readLine().split(" "))
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);
                if (!Objects.equals(edge[0], edge[1])) {
                    if (notInit(graph, edge) || newWeightIsBigger(graph, edge)) {
                        put(graph, edge[0], edge[1], edge[2]);
                        put(graph, edge[1], edge[0], edge[2]);
                    }
                }
            }
            final Set<Integer> notVisited = new HashSet<>(graph.keySet());
            if (notVisited.isEmpty()) {
                return OOPS;
            }

            final PriorityQueue<Edge> edgeHeap = new PriorityQueue<>(Comparator.comparing(Edge::getWeight).reversed());

            int weightSum = 0;
            final boolean[] visited = new boolean[vertexes + 1];
            Integer from = notVisited.iterator().next();
            int visitedCount = 1;
            notVisited.remove(from);

            while (!notVisited.isEmpty()) {
                visited[from] = true;
                final Map<Integer, Integer> adjacent = graph.getOrDefault(from, Collections.emptyMap());
                for (Map.Entry<Integer, Integer> entry : adjacent.entrySet()) {
                    if (!visited[entry.getKey()]) {
                        edgeHeap.add(new Edge(from, entry.getKey(), entry.getValue()));
                    }
                }
                final Edge maxNode = finMaxNode(edgeHeap, visited);

                weightSum += maxNode.getWeight();
                notVisited.remove(maxNode.to);
                visited[maxNode.to] = true;
                from = maxNode.to;
                visitedCount++;

            }
            if (visitedCount != vertexes) {
                return OOPS;
            }

            return String.valueOf(weightSum);
        }
    }

    private static Edge finMaxNode(PriorityQueue<Edge> edgeHeap, boolean[] visited) {
        Edge maxNode;
        do {
            maxNode = edgeHeap.poll();
        } while (visited[maxNode.to]);
        return maxNode;
    }

    private static void put(Map<Integer, Map<Integer, Integer>> graph, Integer from, Integer to, Integer weight) {
        final Map<Integer, Integer> toMap = graph.getOrDefault(from, new HashMap<>());
        toMap.put(to, weight);
        graph.put(from, toMap);
    }

    private static boolean newWeightIsBigger(Map<Integer, Map<Integer, Integer>> graph, Integer[] edge) {
        return graph.getOrDefault(edge[0], Collections.emptyMap()).getOrDefault(edge[1], 0) < edge[2];
    }

    private static boolean notInit(Map<Integer, Map<Integer, Integer>> graph, Integer[] edge) {
        return !graph.containsKey(edge[0]) || !graph.get(edge[0]).containsKey(edge[1]);
    }

    private static class Edge {
        private int getFrom() {
            return from;
        }

        public final int from;

        public int getTo() {
            return to;
        }

        public final int to;
        public final int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        private int getWeight() {
            return weight;
        }
    }
}
