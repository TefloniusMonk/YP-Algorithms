package all;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RomanDigits {
    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final var roman = reader.readLine();
            final var normal = parse(roman);
            return String.valueOf(normal);
        }
    }

    private static int parse(String str) {
        int sum = 0;
        Roman prev = null;
        boolean used = false;
        for (int i = str.length() - 1; i >= 0; i--) {
            final var cur = Roman.of(str.charAt(i));
            if (cur == null) {
                return -1;
            }
            if (!cur.canRepeat() && cur == prev){
                return -1;
            }
            if (prev != null && cur.order < prev.order) {
                if (used || prev.dec != cur.order) {
                    return -1;
                }
                sum -= cur.val;
                used = true;
                continue;
            }
            sum += cur.val;
            used = false;
            prev = cur;
        }
        return sum;
    }

    private enum Roman {
        I(1,  1, null),
        V(2,  5, 1),
        X(3,  10, 1),
        L(4,  50, 3),
        C(5,  100, 3),
        D(6,  500, 5),
        M(7,  1000, 5);

        private final int order;
        private final int val;
        private final Integer dec;

        Roman(int order, int val, Integer dec) {
            this.order = order;
            this.val = val;
            this.dec = dec;
        }

        boolean canRepeat() {
            return this.order % 2 != 0;
        }

        static Roman of(char ch) {
            try {
                return Roman.valueOf(String.valueOf(ch));
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
    }
}
