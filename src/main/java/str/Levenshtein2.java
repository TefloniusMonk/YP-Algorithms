package str;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Levenshtein2 {

    private static final String FAIL = "FAIL";
    private static final String OK = "OK";

    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String str1 = reader.readLine();
            final int str1len = str1.length();
            String str2 = reader.readLine();
            final int str2len = str2.length();
            if (Math.abs(str1.length() - str2.length()) >= 2) {
                return FAIL;
            }
            str1 = str1 + "$"; // For not checking out of bound
            str2 = str2 + "$"; // For not checking out of bound
            if (str1.length() < str2.length()) {
                str1 = str1 + "$";
            }
            if (str2.length() < str1.length()) {
                str2 = str2 + "$";
            }
            final int len = Math.min(str1len, str2len);
            boolean usedAction = false;
            for (int i = 0, j = 0; i <= len && j <= len; i++, j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    continue;
                }
                if (usedAction) {
                    return FAIL;
                }
                usedAction = true;
                if (canReplace(str1, str2, i, j)) {
                    continue;
                }
                if (canAdd(str1, str2, i, j)) {
                    if (str2.charAt(j) == str1.charAt(i + 1)) {
                        j--;
                    } else {
                        i--;
                    }
                    continue;
                }

                return FAIL;
            }
        }
        return OK;
    }

    private static boolean canAdd(String str1, String str2, int i, int j) {
        return str2.charAt(j) == str1.charAt(i + 1)
                || str1.charAt(i) == str2.charAt(j + 1);
    }

    private static boolean canReplace(String str1, String str2, int i, int j) {
        return str1.charAt(i + 1) == str2.charAt(j + 1);
    }

}
