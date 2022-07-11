import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class MinDistance {

    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final String[] firstLine = reader.readLine().split(" ");
            final int vertexes = Integer.parseInt(firstLine[0]);
            final int edges = Integer.parseInt(firstLine[1]);
            final boolean[][] graph = new boolean[vertexes + 1][vertexes + 1];
            for (int i = 0; i < edges; i++) {
                final Integer[] edge = Arrays.stream(reader.readLine().split(" "))
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);
                graph[edge[0]][edge[1]] = true;
                graph[edge[1]][edge[0]] = true;
            }
            final String[] lastLine = reader.readLine().split(" ");
            final int startNode = Integer.parseInt(lastLine[0]);
            final int toFind = Integer.parseInt(lastLine[1]);

            final Set<Integer> visited = new HashSet<>(vertexes);
            final Deque<Pair> queue = new LinkedList<>();
            queue.addLast(new Pair(startNode, 0));
            while (!queue.isEmpty()) {
                final Pair curNode = queue.pop();

                if (curNode.node.equals(toFind)) {
                    return curNode.dist.toString();
                }
                if (visited.contains(curNode.node)) {
                    continue;
                }
                visited.add(curNode.node);

                for (int i = 0; i <= vertexes; i++) {
                    if (graph[curNode.node][i]) {
                        queue.addLast(new Pair(i, curNode.dist + 1));
                    }
                }
            }
            return String.valueOf(-1);
        }
    }

    private static class Pair {
        public final Integer node;
        public final Integer dist;

        private Pair(Integer node, Integer dist) {
            this.node = node;
            this.dist = dist;
        }
    }
}
