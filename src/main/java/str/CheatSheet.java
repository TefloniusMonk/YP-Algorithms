package str;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
    Принцип работы.

    Почти задача о рюкзакe, для каждого префикса длины i ∈ [1, N] пытаемся построить из данных слов строку максимальной длины,
    а в конце проверяем получилась ли у нас исходная строка

    Временная сложность.

    O( N * W ) - N - размер строки, W - суммарный размер словаря. Для каждого префикса строки мы проходим
    по всему словарю и либо пытаемся добавить слово к прошлому префиксу, либо к префиксу длины (prefixLen - word.length)

    Пространственная сложность.

    O(N + W) - где W суммарный размер словаря, N длина строки. Массив dp имеет размер N, массив словаря W
    и строка имеет размер N. Итого 2N + W -> O( N + W )

 */

// ID = 69599186
public class CheatSheet {
    private static final String YES = "YES";
    private static final String NO = "NO";

    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    private static String[] readList(BufferedReader reader, int size) throws IOException {
        final String[] arr = new String[size];
        for (int i = 0; i < size; i++) {
            arr[i] = reader.readLine();
        }
        return arr;
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final String str = reader.readLine();
            final int wordCount = Integer.parseInt(reader.readLine());
            final String[] dict = readList(reader, wordCount);
            final int[] dp = new int[str.length() + 1];
            Arrays.fill(dp, -1);
            dp[0] = 0;
            for (int i = 1; i < dp.length; i++) {
                final int prev = dp[i - 1];
                for (String word : dict) {
                    if (word.length() > i) {
                        continue;
                    }
                    int prefixLen = prev + word.length();
                    if (prefixLen == i && isSubstring(str, prev, word)) {
                        dp[i] = prefixLen;
                        break;
                    }
                    prefixLen = dp[i - word.length()] + word.length();
                    if (prefixLen == i && isSubstring(str, i - word.length(), word)) {
                        dp[i] = prefixLen;
                        break;
                    }
                }
                if (dp[i] == -1) {
                    dp[i] = prev;
                }
            }
            return dp[str.length()] == str.length() ? YES : NO;
        }
    }


    private static boolean isSubstring(String str, int start, String word) {
        for (int i = start, j = 0; i < start + word.length(); i++, j++) {
            if (str.charAt(i) != word.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
