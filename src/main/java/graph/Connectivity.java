package graph;

import java.io.*;
import java.util.*;

public class Connectivity {
    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final String[] firstLine = reader.readLine().split(" ");
            final int vertexes = Integer.parseInt(firstLine[0]);
            final int edges = Integer.parseInt(firstLine[1]);
            final Map<Integer, List<Integer>> graph = new HashMap<>(vertexes + 700);

            for (int i = 0; i < edges; i++) {
                final Integer[] edge = Arrays.stream(reader.readLine().split(" "))
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);
                final List<Integer> adjacent = graph.getOrDefault(edge[0], new ArrayList<>());
                adjacent.add(edge[1]);
                graph.put(edge[0], adjacent);

                final List<Integer> adjacentInversed = graph.getOrDefault(edge[1], new ArrayList<>());
                adjacentInversed.add(edge[0]);
                graph.put(edge[1], adjacentInversed);
            }

            final StringBuilder builder = new StringBuilder();
            int connectivityCount = 0;
            final boolean[] visited = new boolean[vertexes + 1];
            for (int j = 1; j <= vertexes; j++) {
                if (visited[j]) {
                    continue;
                }
                final Deque<Integer> stack = new LinkedList<>();
                stack.push(j);

                int maxIdx = -1;
                final boolean[] connectivityLst = new boolean[vertexes];
                while (!stack.isEmpty()) {
                    final Integer curNode = stack.pop();
                    maxIdx = Math.max(maxIdx, curNode);
                    if (visited[curNode]) {
                        continue;
                    } else {
                        visited[curNode] = true;
                    }
                    connectivityLst[curNode - 1] = true;
                    final List<Integer> adjacent = graph.get(curNode);
                    if (adjacent == null) {
                        continue;
                    }
                    adjacent.forEach(stack::push);
                }
                for (int i = 1; i <= maxIdx; i++) {
                    if (connectivityLst[i - 1]) {
                        builder.append(i).append(" ");
                    }
                }
                builder.append("\n");

                connectivityCount++;
            }
            return builder
                    .insert(0, "\n")
                    .insert(0, connectivityCount)
                    .toString();
        }
    }
}
