import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AdjacentMatrix {
    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final var reader = new BufferedReader(new InputStreamReader(is))) {
            final var firstLine = reader.readLine().split(" ");
            final var vrtxs = Integer.parseInt(firstLine[0]);
            final var edges = Integer.parseInt(firstLine[1]);
            final var graph = new int[101][101];
            var maxEdge = -1;
            for (int i = 1; i <= edges; i++) {
                final var edge = Arrays.stream(reader.readLine().split(" "))
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);
                graph[edge[0]][edge[1] - 1] = 1;
                maxEdge = Math.max(maxEdge, edge[0]);
            }
            final var builder = new StringBuilder();
            for (int i = 1; i <= maxEdge; i++) {
                builder.append(Arrays.stream(graph[i])
                                .limit(maxEdge)
                                .mapToObj(String::valueOf)
                                .collect(Collectors.joining(" ")))
                        .append("\n");
            }
            return builder.toString().strip();
        }
    }
}
