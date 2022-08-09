package str;

import java.util.*;
import java.io.*;

public class Reverse {
    public static void main(String[] args) throws Exception {
        try (final var reader = new BufferedReader(new InputStreamReader(System.in))) {
            final var str = reader.readLine();
            final var split = str.split(" ");
            final var builder = new StringBuilder();
            for (int i = split.length - 1; i >= 0; i--) {
                builder.append(split[i]).append(" ");
            }
            System.out.println(builder.toString());
        }
    }

}