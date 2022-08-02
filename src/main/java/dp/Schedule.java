package dp;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Schedule {
    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final int len = Integer.parseInt(reader.readLine());
            if (len == 0) {
                return "0";
            }
            final List<Lesson> lessons = new ArrayList<>(len);
            for (int i = 0; i < len; i++) {
                lessons.add(new Lesson(reader.readLine().split(" ")));
            }
            final Lesson[] arr = lessons.stream()
                    .sorted(Comparator.comparing((Lesson o) -> o.end).thenComparing(o -> o.start))
                    .toArray(Lesson[]::new);
            final StringBuilder builder = new StringBuilder();
            int total = 0;
            BigDecimal minStart = null;
            for (int i = 0; i < len; i++) {
                final Lesson lesson = arr[i];
                if (minStart != null && lesson.start.compareTo(minStart) < 0) {
                    continue;
                }
                total++;
                minStart = lesson.end;
                builder.append(lesson.start)
                        .append(" ")
                        .append(lesson.end)
                        .append("\n");
            }
            return builder
                    .insert(0, total + "\n")
                    .toString();
        }
    }

    private static class Lesson {


        public final BigDecimal start;
        public final BigDecimal end;

        private Lesson(String[] times) {
            this.start = new BigDecimal(times[0]);
            this.end = new BigDecimal(times[1]);
        }

        private BigDecimal getStart() {
            return start;
        }

        private BigDecimal getEnd() {
            return end;
        }
    }
}