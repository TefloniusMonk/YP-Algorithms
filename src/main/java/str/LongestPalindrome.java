package str;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class LongestPalindrome {
    private static final Character[] DICT = Arrays.stream("a b c d e f g h i j k l m n o p q r s t u v w x y z"
                    .split(" "))
            .map(str -> str.charAt(0))
            .collect(Collectors.toList())
            .toArray(Character[]::new);

    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final var str = reader.readLine();
            if (str == null || str.isEmpty()){
                return "";
            }
            final var builder = new StringBuilder();
            final var map = new LinkedHashMap<Character, Integer>();
            for (int i = 0; i < str.length(); i++) {
                final var ch = str.charAt(i);
                map.put(ch, map.getOrDefault(ch, 0) + 1);
            }
            String lastCh = "";
            for (var ch : DICT) {
                final var chCount = map.getOrDefault(ch, 0);
                if (chCount == 0) {
                    continue;
                }
                if (chCount % 2 != 0 && Objects.equals(lastCh, "")) {
                    lastCh = String.valueOf(ch);
                }
                for (int i = 0; i < chCount / 2; i++) {
                    builder.append(ch);
                }
            }
            final var halfStr = builder.toString();
            final var secondHalfBuilder = new StringBuilder();
            for (int i = 0; i < halfStr.length(); i++) {
                secondHalfBuilder.insert(0, halfStr.charAt(i));
            }
            return halfStr + lastCh + secondHalfBuilder;
        }
    }
}
