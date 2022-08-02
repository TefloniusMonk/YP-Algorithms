package hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;


public class PolyHashNaive {
    private static int pow(int num, int pow, int mod) {
        if (pow == 0) return 1;
        int res = num;
        while (pow != 0) {
            res = (res * num);
            pow--;
        }
        return res;
    }

    public static int polyHash(int a, int mod, String str) {
        BigDecimal hash = new BigDecimal(0);
        for (int i = 0; i < str.length(); i++) {
            final var ch = str.charAt(str.length() - i - 1);
            final var pow = BigDecimal.valueOf(a)
                    .pow(i)
                    .multiply(BigDecimal.valueOf((int) ch));
            hash = (hash.add(pow));
        }
        return hash.remainder(BigDecimal.valueOf(mod)).intValue();
    }


    public static void main(String[] args) throws IOException {
        try (final var reader = new BufferedReader(new InputStreamReader(System.in))) {
            final var a = Integer.parseInt(reader.readLine());
            final var m = Integer.parseInt(reader.readLine());
            final var str = reader.readLine();
            System.out.println(polyHash(a, m, str));
        }
    }
}