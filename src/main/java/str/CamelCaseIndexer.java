package str;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class CamelCaseIndexer {
    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final int classCount = Integer.parseInt(reader.readLine());
            final List<String> classes = readList(reader, classCount).stream()
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
            final int requestCount = Integer.parseInt(reader.readLine());
            final List<String> requests = readList(reader, requestCount);
            final var trie = new Trie(classes);
            final var result = requests.stream()
                    .flatMap(it -> trie.getAll(it).stream()
                            .distinct()
                            .sorted())
                    .collect(Collectors.joining("\n"));
            return result;
        }
    }

    private static List<String> readList(BufferedReader reader, int count) throws IOException {
        final List<String> lst = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            lst.add(reader.readLine());
        }
        return lst;
    }

    private static class Trie {
        private final Node root;

        private Trie(List<String> strings) {
            root = new Node();
            strings.forEach(this::add);
        }

        public void add(String s) {
            root.add(s, -1);
        }

        public Set<String> getAll(String s) {
            final Set<String> collector = new HashSet<>();
            root.getAllRecursively(collector, s, 0);
            return collector;
        }
    }

    private static class Node {
        int size = 0;
        final Map<Character, Node> nextNodes = new HashMap<>();
        final List<String> vals = new ArrayList<>();

        public void add(String s, int idx) {
            if (s.isEmpty()) {
                vals.add(s);
                return;
            }
            for (int i = idx + 1; i < s.length(); i++) {
                final var ch = s.charAt(i);
                if (Character.isUpperCase(ch)) {
                    size++;
                    final var node = nextNodes.getOrDefault(ch, new Node());
                    node.add(s, i);
                    nextNodes.put(ch, node);
                    return;
                }
            }
            vals.add(s);
        }

        public void getAllRecursively(Set<String> collector, String letters, int idx) {
            if (letters.isEmpty()) {
                getAllRecursively(collector);
                return;
            }
            if (size == 0) {
                return;
            }
            if (idx < letters.length() - 1) {
                final var nextNode = nextNodes.get(letters.charAt(idx));
                if (nextNode != null) {
                    nextNode.getAllRecursively(collector, letters, idx + 1);
                }
                return;
            }
            if (idx == letters.length() - 1) {
                final var nextNode = nextNodes.get(letters.charAt(idx));
                if (nextNode != null) {
                    nextNode.getAllRecursively(collector);
                }
            }
        }

        private void getAllRecursively(Set<String> collector) {
            if (vals.isEmpty() && nextNodes.isEmpty()) {
                return;
            }
            collector.addAll(vals);
            nextNodes.values().forEach(node -> node.getAllRecursively(collector));
        }
    }
}
