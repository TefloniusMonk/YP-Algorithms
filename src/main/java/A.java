import java.util.stream.Collectors;
import java.io.*;
import java.util.*;


public class A {

    public static void main(String[] args) throws IOException {
        try (final var reader = new BufferedReader(new InputStreamReader(System.in))) {
            final var size = Integer.parseInt(reader.readLine());
            final var map = readAndCount(reader);
            final var maxK = Integer.parseInt(reader.readLine());
            System.out.println(
                    map.entrySet().stream()
                            .sorted((e1,e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                            .limit(maxK)
                            .map(Map.Entry::getKey)
                            .collect(Collectors.joining(" "))
            );
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static Map<String, Integer> readAndCount(BufferedReader reader) throws IOException {
        var map = new HashMap<String, Integer>();
        var st = new StringTokenizer(reader.readLine());
        while (st.hasMoreTokens()) {
            var next = st.nextToken();
            map.put(next, map.getOrDefault(next,0) + 1);
        }
        return map;
    }
}