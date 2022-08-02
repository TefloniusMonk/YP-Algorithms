package hash;

import java.io.*;
import java.util.*;


public class Substrings {
    public static int maxSubString(String str){
        int max = 1;
        final var chars = new LinkedHashSet<Character>();
        for (int i = 0; i < str.length(); i++){
            final var ch = str.charAt(i);
            if (chars.contains(ch)) {
                remove(chars, ch);
            }
            chars.add(ch);
            max = Math.max(max, chars.size());
        }

        return max;
    }

    public static void remove(LinkedHashSet<Character> set, Character toRemove){
        if (set.isEmpty()) return;
        var ch = set.iterator().next();
        set.remove(ch);
        while(!set.isEmpty() && ch != toRemove){
            ch = set.iterator().next();
            set.remove(ch);
        }
    }


    public static void main(String[] args) throws IOException {
        try (final var reader = new BufferedReader(new InputStreamReader(System.in))) {
            final var str = reader.readLine();
            System.out.println(maxSubString(str));
        }
    }
}