import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

class CommonSubArray {
    private static final long Q = (long) Math.pow(10, 9) + 7;
    private static final long MOD = (long) Math.pow(2, 32);
    private static long[] pows;
    private static long[] prefixes1;
    private static long[] prefixes2;

    private static long[] initPows(int n) {
        final long[] pows = new long[n + 1];
        pows[0] = 1;
        for (int i = 1; i < pows.length; i++) {
            pows[i] = pows[i - 1] * Q % MOD;
        }
        return pows;
    }

    private static long[] initPrefixes(List<Integer> arr) {
        final long[] prefixes = new long[arr.size() + 1];
        prefixes[0] = 0;
        for (int i = 1; i < prefixes.length; i++) {
            prefixes[i] = prefixes[i - 1] * Q % MOD + arr.get(i - 1) % MOD;
        }
        return prefixes;
    }

    public static int maxSubarraySize(List<Integer> arr1, List<Integer> arr2) {
        int res = 0;
        int low = 1, hi = Math.min(arr1.size(), arr2.size());
        pows = initPows(hi);
        prefixes1 = initPrefixes(arr1);
        prefixes2 = initPrefixes(arr2);
        while (low <= hi) {
            final var mid = (low + hi) / 2;
            if (existCommonSubArray(arr1, arr2, mid)) {
                res = mid;
                low = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return res;
    }

    private static boolean existCommonSubArray(List<Integer> arr1, List<Integer> arr2, int size) {
        final long pow = pows[size -1];
        final var map = new HashMap<Long, Integer>(); // hash to idx
        var hash1 = prefixes1[size];
        map.put(hash1, 0);
        for (int i = 1; i <= arr1.size() - size; i++) {
            final var prevCh = arr1.get(i - 1);
            final var nextCh = arr1.get(i + size - 1);
            hash1 = updateHash(pow, hash1, prevCh, nextCh);
            if (!map.containsKey(hash1)) {
                map.put(hash1, i);
            }
        }

        var hash2 = prefixes2[size];
        if (map.containsKey(hash2)) {
            final var idx = map.get(hash2);
            if (eq(arr1.subList(idx, idx + size), arr2.subList(0, size))) {
                return true;
            }
        }
        for (int i = 1; i <= arr2.size() - size; i++) {
            final var prevCh = arr2.get(i - 1);
            final var nextCh = arr2.get(i + size - 1);
            hash2 = updateHash(pow, hash2, prevCh, nextCh);
            if (map.containsKey(hash2)) {
                final var idx = map.get(hash2);
                if (eq(arr1.subList(idx, idx + size), arr2.subList(i, i + size))) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean eq(List<Integer> lst1, List<Integer> lst2) {
        if (lst1.size() != lst2.size()) {
            return false;
        }
        for (int i = 0; i < lst1.size(); i++) {
            if (!Objects.equals(lst1.get(i), lst2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static long updateHash(long pow, long hash, Integer prevCh, Integer nextCh) {
        return (((hash - (prevCh * pow % MOD) + MOD) % MOD) * Q % MOD + nextCh + MOD) % MOD;
    }


    public static void main(String[] args) throws IOException {
        final var result = inputWrapper(System.in);
        System.out.println(result);
    }

    public static Integer inputWrapper(InputStream inputStream) throws IOException {
        try (final var reader = new BufferedReader(new InputStreamReader(inputStream))) {
            final var size1 = Integer.parseInt(reader.readLine());
            final var arr1 = readList(reader, size1);
            final var size2 = Integer.parseInt(reader.readLine());
            final var arr2 = readList(reader, size2);
            return maxSubarraySize(arr1, arr2);
        }
    }

    private static List<Integer> readList(BufferedReader reader, int size) throws IOException {
        final var lst = new ArrayList<Integer>(size);
        final var tokenizer = new StringTokenizer(reader.readLine());
        while (tokenizer.hasMoreTokens()) {
            final var token = tokenizer.nextToken();
            lst.add(Integer.parseInt(token));
        }
        return lst;
    }

}