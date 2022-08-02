package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
    Принцип работы.

    Классический алгоритм Вагнера-Фишера для нахождения расстояния Левенштейна. Для каждого префикса первой строки
    вычисляем расстояние каждого префикса второй. Для нулевых префиксов (Когда i или j == 0) берем длину второго префикса,
    для ненулевых строк, если символы совпали, то берем стоимость dp[i-1][j-1].
    Если символы не совпали, то берем минимальное по стоимости действие(вставка, удаление, замена)

    Временная сложность.

    O(N * M) - где N и M длины строк, т.к. для каждого символа первой строки мы сравниваем каждый символ второй

    Пространственная сложность.

    O(N * M) - Массив dp размера N x M, где N и M длины строк

 */

// ID = 69553342
public class Levenshtein {

    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final String s1 = reader.readLine();
            final String s2 = reader.readLine();

            final short[][] dp = new short[s1.length() + 1][s2.length() + 1];

            for (short i = 0; i < dp.length; i++) {
                for (short j = 0; j <= s2.length(); j++) {
                    if (i == 0) {
                        dp[i][j] = j;
                        continue;
                    }
                    if (j == 0) {
                        dp[i][j] = i;
                        continue;
                    }
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = (short) Math.min(
                                insert(dp, i, j),
                                Math.min(remove(dp, i, j),
                                        replace(dp, i, j)));

                    }

                }
            }
            return String.valueOf(dp[s1.length()][s2.length()]);
        }
    }

    private static int insert(short[][] dp, short i, short j) {
        return dp[i][j - 1] + 1;
    }

    private static int remove(short[][] dp, short i, short j) {
        return dp[i - 1][j] + 1;
    }

    private static int replace(short[][] dp, short i, short j) {
        return dp[i - 1][j - 1] + 1;
    }
}
