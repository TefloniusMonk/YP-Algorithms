package str;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CommonPrefix {
    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final int strCount = Integer.parseInt(reader.readLine());
            if (strCount == 0) {
                return "0";
            }
            final ArrayList<String> strs = new ArrayList<String>(strCount);
            int minLen = Integer.MAX_VALUE;
            for (int i = 0; i < strCount; i++) {
                final String line = reader.readLine();
                minLen = Math.min(minLen, line.length());
                strs.add(line);
            }
            int commonPrefixLen = 0;
            final String sample = strs.get(0);
            for (int i = 0; i < minLen; i++) {
                final char curChar = sample.charAt(i);
                for (int j = 1; j < strCount; j++) {
                    if (strs.get(j).charAt(i) != curChar) {
                        return String.valueOf(commonPrefixLen);
                    }
                }
                commonPrefixLen++;
            }
            return String.valueOf(commonPrefixLen);
        }
    }
}
