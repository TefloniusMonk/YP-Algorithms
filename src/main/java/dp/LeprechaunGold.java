package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LeprechaunGold {

    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final String[] firstLine = reader.readLine().split(" ");
            final int goldBars = Integer.parseInt(firstLine[0]);
            final int bagCapacity = Integer.parseInt(firstLine[1]);
            if (bagCapacity == 0) {
                return "0";
            }
            final short[] barWeights = new short[goldBars + 1];
            barWeights[0] = 0;
            final StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 1; i <= goldBars; i++) {
                barWeights[i] = Short.parseShort(tokenizer.nextToken());
                if (barWeights[i] == bagCapacity) {
                    return "" + bagCapacity;
                }
            }
            Arrays.sort(barWeights);
            final int[][] dp = new int[barWeights.length][bagCapacity + 1];
            for (int barIdx = 1; barIdx <= goldBars; barIdx++) {
                for (int j = 1; j < Math.min(bagCapacity + 1, barWeights[barIdx]); j++) {
                    dp[barIdx][j] = dp[barIdx - 1][j];
                }
                for (int curCapacity = barWeights[barIdx]; curCapacity <= bagCapacity; curCapacity++) {
                    int withNewBar = barWeights[barIdx] + dp[barIdx - 1][Math.max(0, curCapacity - barWeights[barIdx])];
                    if (withNewBar > curCapacity) {
                        final int leftWeight = (curCapacity - barWeights[barIdx]);
                        withNewBar = barWeights[barIdx] + dp[barIdx - 1][leftWeight];
                    }
                    dp[barIdx][curCapacity] = Math.max(
                            dp[barIdx - 1][curCapacity],
                            withNewBar
                    );
                }
            }

            return String.valueOf(dp[goldBars][bagCapacity]);
        }
    }
}
