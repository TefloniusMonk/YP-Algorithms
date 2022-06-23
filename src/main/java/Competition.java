import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Competition {
    public static int findMaxDraw(List<Integer> arr) {
        int max = 0;
        int sum = 0;

        final int[] sums = new int[arr.size()];
        final var maxLeft = new HashMap<Integer, Integer>();
        maxLeft.put(0, -1);

        for (int i = 0; i < arr.size(); i++) {
            sum = sum + arr.get(i);
            sums[i] = sum;
            if (!maxLeft.containsKey(sum)) {
                maxLeft.put(sum, i);
            } else {
                max = Math.max(i - maxLeft.get(sum), max);
            }
        }

        if (sums[arr.size() - 1] + arr.get(arr.size() - 1) == 0) {
            return arr.size();
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        try (final var reader = new BufferedReader(new InputStreamReader(System.in))) {
            final var size = Integer.parseInt(reader.readLine());
            if (size <= 1) {
                System.out.println(0);
                return;
            }
            final var arr = readList(reader, size);
            final var result = findMaxDraw(arr);

            System.out.println(result);

        }
    }

    private static List<Integer> readList(BufferedReader reader, int size) throws IOException {
        final var lst = new ArrayList<Integer>(size);
        final var tokenizer = new StringTokenizer(reader.readLine());
        while (tokenizer.hasMoreTokens()) {
            final var token = Integer.parseInt(tokenizer.nextToken());
            lst.add(token == 0 ? -1 : 1);
        }
        return lst;
    }
}