package dp;

import java.util.*;
import java.io.*;

public class Exchange {
    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final int len = Integer.parseInt(reader.readLine());
            if (len < 2) {
                return "0";
            }
            final int[] prices = new int[len];
            final StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < len; i++) {
                prices[i] = Integer.parseInt(tokenizer.nextToken());
            }
            int profit = 0;
            int buy = -1;
            for (int i = 0; i < len; i++) {
                if (buy == -1 && min(prices, i)) {
                    buy = prices[i];
                    continue;
                }
                if (buy != -1 && max(prices, i)) {
                    profit += prices[i] - buy;
                    buy = -1;
                }
            }
            return String.valueOf(profit);
        }
    }

    private static boolean max(int[] prices, int day) {
        return (day == prices.length - 1) ||
                (prices[day + 1] < prices[day]);
    }

    private static boolean min(int[] prices, int day) {
        return day != prices.length - 1 && (day == 0 && prices[day + 1] > prices[day]) ||
                (day != 0 && prices[day - 1] >= prices[day] &&
                        day != prices.length - 1 && prices[day + 1] > prices[day]);
    }
}