import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AdjacencyList {
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
                maxEdge = Math.max(maxEdge, edge[0]);
            }
            final var builder = new StringBuilder();
            for (int i = 1; i <= maxEdge; i++) {
                final var edgesLst = graph.get(i);
                if (edgesLst == null) {
                    builder.append(0);
                } else {
                    builder.append(edgesLst.size())
                            .append(" ")
                            .append(edgesLst.stream()
                                    .sorted()
                                    .map(String::valueOf)
                                    .collect(Collectors.joining(" ")));
                }
                builder.append("\n");
            }
            return builder.toString().strip();
        }
    }
}
