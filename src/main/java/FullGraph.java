import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class FullGraph {
    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final String[] firstLine = reader.readLine().split(" ");
            final int vertexes = Integer.parseInt(firstLine[0]);
            final int edges = Integer.parseInt(firstLine[1]);
            final boolean[][] graph = new boolean[vertexes][vertexes];
            for (int i = 0; i < edges; i++) {
                final Integer[] edge = Arrays.stream(reader.readLine().split(" "))
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);
                graph[edge[0] - 1][edge[1] - 1] = true;
                graph[edge[1] - 1][edge[0] - 1] = true;
            }
            for (int i = 0; i < vertexes; i++) {
                for (int j = 0; j < vertexes; j++) {
                    if (i == j) {
                        continue;
                    }
                    if (!graph[i][j]) {
                        return "NO";
                    }
                }
            }
            return "YES";
        }
    }

}
