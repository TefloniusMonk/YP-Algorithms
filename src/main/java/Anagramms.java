import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Anagramms {

    public static void main(String[] args) throws IOException {
        try (final var reader = new BufferedReader(new InputStreamReader(System.in))) {
            final var size = Integer.parseInt(reader.readLine());
            final var lst = readList(reader, size);
            lst.forEach(System.out::println);
        }
    }

    private static List<String> readList(BufferedReader reader, int size) throws IOException {
        final var map = new HashMap<String, List<Integer>>(size);
        final var tokenizer = new StringTokenizer(reader.readLine());
        for (int j = 0; j < size; j++) {
            final var line = tokenizer.nextToken().toCharArray();
            Arrays.sort(line);
            final var sorted = String.valueOf(line);
            if (map.containsKey(sorted)) {
                final var lst = map.get(sorted);
                lst.add(j);
            } else {
                final var lst = new ArrayList<Integer>();
                lst.add(j);
                map.put(sorted, lst);
            }
        }
        return map.values().stream()
                .map(integers -> integers.stream().sorted().map(String::valueOf).collect(Collectors.joining(" ")))
                .sorted((Comparator.comparingInt(o -> Integer.parseInt(o.split(" ")[0]))))
                .collect(Collectors.toList());
    }
}