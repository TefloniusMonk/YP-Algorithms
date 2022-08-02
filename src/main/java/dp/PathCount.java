package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class PathCount {
    private static final int MOD = (int) (Math.pow(10, 9) + 7);
    private static final int WHITE = 0;
    private static final int GREY = 1;
    private static final int BLACK = 2;


    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final String[] firstLine = reader.readLine().split(" ");
            final int vertexes = Integer.parseInt(firstLine[0]);
            final int edgesCount = Integer.parseInt(firstLine[1]);
            final HashMap<Integer, List<Integer>> graph = new HashMap<>(vertexes);
            for (int i = 0; i < edgesCount; i++) {
                final Integer[] edge = Arrays.stream(reader.readLine().split(" "))
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);
                if (!Objects.equals(edge[0], edge[1])) {
                    final List<Integer> adjacent = graph.getOrDefault(edge[0], new ArrayList<>());
                    adjacent.add(edge[1]);
                    graph.put(edge[0], adjacent);
                }
            }
            final String[] lastLine = reader.readLine().split(" ");
            final int start = Integer.parseInt(lastLine[0]);
            final int end = Integer.parseInt(lastLine[1]);

            final List<Integer> sorted = sort(graph, vertexes);

            final int[] dp = new int[vertexes + 1];
            dp[start] = 1;
            for (Integer from : sorted) {
                for (Integer to : graph.getOrDefault(from, Collections.emptyList())) {
                    dp[to] = (int) ((long) dp[to] + dp[from]) % MOD;
                }
            }

            return String.valueOf(dp[end]);
        }
    }

    private static List<Integer> sort(HashMap<Integer, List<Integer>> graph, int vertexes) {
        final LinkedList<Integer> collector = new LinkedList<>();
        final int[] visited = new int[vertexes + 1];
        for (int i = 1; i <= vertexes; i++) {
            if (visited[i] != WHITE) {
                continue;
            }
            final Deque<Integer> stack = new LinkedList<>();
            stack.push(i);
            while (!stack.isEmpty()) {
                final Integer curNode = stack.pop();
                if (visited[curNode] == BLACK) {
                    continue;
                }
                if (visited[curNode] == GREY) {
                    visited[curNode] = BLACK;
                    collector.addFirst(curNode);
                    continue;
                }
                visited[curNode] = GREY;
                stack.addFirst(curNode);
                final List<Integer> adjacent = graph.get(curNode);
                if (adjacent == null) {
                    continue;
                }
                adjacent.stream()
                        .filter(vert -> visited[vert] == WHITE)
                        .forEach(stack::addFirst);
            }
        }
        return new ArrayList<>(collector);
    }
}
