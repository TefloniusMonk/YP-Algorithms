package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
    Принцип работы.

    Небольшой инсайт:
        Если мы хотим разделить массив на две части так, что суммы двух образовавшихся массивов равны,
    то эта сумма равна половине изначального массива.
    Пусть сумма всех чисел равна sum,
    а суммы первого и второго образованных массивов sum1 и sum2 соответственно, тогда:
        sum1 = sum2 => sum1 + sum2 = 2 * sum1
    при этом:
        sum1 + sum2 = sum => sum = 2 * sum1
    из чего следует, что сумма всех чисел должна быть четной.
    Дальше нам надо решить задачу, можем ли мы выбрать такие числа, чтобы их сумма была равна sum/2.
    Можно ее переформулировать так: какую самую большую сумму, не больше sum/2, мы можем набрать из данных чисел
    и будет ли она равна sum/2.
    А это уже знакомая нам задача о рюкзаке, только в данном случае нас интересует одна ячейка массива dp, а именно dp[sum/2].
    В данном случае нам не надо хранить двумерный массив, а просто проверить можем ли мы каким нибудь образом набрать j-ую сумму,
    где j ∈ [1, sum/2] и dp[0] = true
    Проходим по всем n ∈ nums и по всем числам от sum/2 до n (От большего к меньшему, иначе при числе 1 мы весь массив сделаем true)
    и просваиваем dp[i] = dp[i] || dp[j - num] (так мы учтем и само число num когда j == num, т.к. dp[0]=true)

    Временная сложность.

    O(N * M) - где N кол-во чисел, а M сумма всех чисел

    Пространственная сложность.

    O(N + M) - где N кол-во чисел, а M сумма всех чисел

 */

// ID = 69553342
public class SameSum {
    private static final String TRUE = "True";
    private static final String FALSE = "False";

    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final int len = Integer.parseInt(reader.readLine());
            final short[] nums = new short[len + 1]; // one indexed arr
            int sum = 0;
            final StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 1; i <= len; i++) {
                final short num = Short.parseShort(tokenizer.nextToken());
                nums[i] = num;
                sum += num;
            }
            if (sum % 2 != 0) {
                return FALSE;
            }
            final int halfSum = sum / 2;
            final boolean[] dp = new boolean[halfSum + 1];
            dp[0] = true;
            for (int i = 1; i < nums.length; i++) {
                final short num = nums[i];
                for (int j = halfSum; j >= num; j--) {
                    dp[j] |= dp[j - num];
                }
            }
            return dp[halfSum] ? TRUE : FALSE;
        }
    }
}
