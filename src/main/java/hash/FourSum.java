package hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;


public class FourSum {
    public static String findFourSums(List<Integer> lst, int sum) {
        final var map = new HashMap<Long, List<Pair>>();
        for (int i = 0; i < lst.size(); i++) {
            for (int j = i + 1; j < lst.size(); j++) {
                final var curSum = (long) lst.get(i) + lst.get(j);
                final var pairs = map.getOrDefault(curSum, new ArrayList<>());
                pairs.add(new Pair(i, j));
                map.put(curSum, pairs);
            }
        }
        final var result = new HashSet<Four>();
        for (var key : map.keySet()) {
            if (map.containsKey(sum - key)) {
                for (var leftPair : map.get(key)) {
                    for (var rightPair : map.get(sum - key)) {
                        if (leftPair.differ(rightPair)) {
                            result.add(new Four(
                                    lst.get(leftPair.left),
                                    lst.get(leftPair.right),
                                    lst.get(rightPair.left),
                                    lst.get(rightPair.right)));
                        }
                    }
                }
            }
        }
        System.out.println(result.size());
        return result.stream().map(Four::toString).sorted((s1, s2) -> {
            final var sp1 = s1.split(" ");
            final var sp2 = s2.split(" ");
            final var compareFirst = Integer.compare(Integer.parseInt(sp1[0]), Integer.parseInt(sp2[0]));
            if (compareFirst != 0) {
                return compareFirst;
            }
            return Integer.compare(Integer.parseInt(sp1[1]), Integer.parseInt(sp2[1]));
        }).collect(Collectors.joining("\n"));
    }

    public static void main(String[] args) throws IOException {
        try (final var reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int size = Integer.parseInt(reader.readLine());
            if (size == 0) {
                System.out.println(0);
                return;
            }
            final int sum = Integer.parseInt(reader.readLine());
            final var lst = readList(reader);
            final var res = findFourSums(lst, sum);
            if (!res.isEmpty()) {
                System.out.println(res);
            }
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
    }

    private static class Pair {
        public final int left;
        public final int right;

        public Pair(int i1, int i2) {
            this.left = i1;
            this.right = i2;
        }

        public boolean differ(Pair other) {
            return left != other.left &&
                    left != other.right &&
                    right != other.left &&
                    right != other.right;
        }
    }
}

class Four {
    private final int[] four;

    Four(int... idxs) {
        four = Arrays.stream(idxs).sorted().toArray();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Four four1 = (Four) o;

        return Arrays.equals(four, four1.four);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(four);
    }

    @Override
    public String toString() {
        return Arrays.stream(four).mapToObj(String::valueOf).collect(Collectors.joining(" "));
    }


}


