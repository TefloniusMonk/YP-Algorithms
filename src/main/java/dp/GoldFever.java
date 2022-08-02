package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class GoldFever {

    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            int capacity = Integer.parseInt(reader.readLine());
            final int pileCount = Integer.parseInt(reader.readLine());
            final PriorityQueue<Pile> pileHeap = new PriorityQueue<>(pileCount,
                    Comparator.comparing(Pile::getCostPerKg).reversed());
            for (int i = 0; i < pileCount; i++) {
                pileHeap.add(new Pile(reader.readLine().split(" ")));
            }
            long sum = 0;
            while (!pileHeap.isEmpty() && capacity > 0) {
                final Pile pile = pileHeap.poll();
                if (pile.mass <= capacity) {
                    capacity -= pile.mass;
                    sum += (long) pile.mass * pile.costPerKg;
                    continue;
                }
                sum += (long) pile.costPerKg * capacity;
                break;
            }
            return String.valueOf(sum);
        }
    }

    public static class Pile {
        public final int costPerKg;
        public final int mass;

        public int getCostPerKg() {
            return costPerKg;
        }

        public Pile(String[] str) {
            this.costPerKg = Integer.parseInt(str[0]);
            this.mass = Integer.parseInt(str[1]);
        }
    }
}
