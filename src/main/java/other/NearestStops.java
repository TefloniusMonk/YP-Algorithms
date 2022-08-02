package other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class NearestStops {
    private static final int MAX_DIST = 20;
    private static final int MAX_DIST_SQUARED = MAX_DIST * MAX_DIST;

    public static void main(String[] args) throws IOException {
        final String result = inputWrapper(System.in);
        System.out.println(result);
    }

    public static String inputWrapper(InputStream inputStream) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            final int metroCount = Integer.parseInt(reader.readLine());
            final List<Coordinate> metros = readList(reader, metroCount);
            final int busStopsCount = Integer.parseInt(reader.readLine());
            final List<Coordinate> busStops = readList(reader, busStopsCount);
            return findMaxNearest(metros, busStops);
        }
    }

    private static String findMaxNearest(List<Coordinate> metros, List<Coordinate> busStops) {
        int max = 0;
        int maxIdx = -1;
        final var sortedStops = busStops.stream()
                .sorted(Comparator.comparing(Coordinate::x).thenComparing(Coordinate::y))
                .collect(Collectors.toList());
        for (int i = 0; i < metros.size(); i++) {
            final var metro = metros.get(i);
            int curMetroCount = 0;
            final var leftBound = maxNearestLeft(sortedStops, metro);
            final var rightBound = maxNearestRight(sortedStops, metro);
            for (int j = leftBound; j <= rightBound; j++) {
                final var curDist = getDistSquared(metro, sortedStops.get(j));
                if (curDist <= MAX_DIST_SQUARED) {
                    curMetroCount++;
                }
            }
            if (curMetroCount > max) {
                max = curMetroCount;
                maxIdx = i;
            }
        }
        return String.valueOf(maxIdx + 1);
    }

    private static int maxNearestLeft(List<Coordinate> sortedStops, int low, int hi, int leftBound) {
        if (low >= hi) {
            return low;
        }
        final var mid = (hi + low) / 2;
        final var midVal = sortedStops.get(mid);
        if (midVal.x < leftBound) {
            return maxNearestLeft(sortedStops, mid + 1, hi, leftBound);
        } else {
            return maxNearestLeft(sortedStops, low, mid, leftBound);
        }
    }

    private static int maxNearestRight(List<Coordinate> sortedStops, int low, int hi, int rightBound) {
        if (low + 1 >= hi) {
            if (sortedStops.get(hi).x <= rightBound){
                return hi;
            }
            return low;
        }
        final var mid = ((hi + low) / 2) + 1;
        final var midVal = sortedStops.get(mid);
        if (midVal.x <= rightBound) {
            return maxNearestRight(sortedStops, mid, hi, rightBound);
        } else {
            return maxNearestRight(sortedStops, low, mid - 1 , rightBound);
        }
    }

    public static int maxNearestLeft(List<Coordinate> sortedStops, Coordinate metro) {
        final int leftBound = metro.x - MAX_DIST;
        return maxNearestLeft(sortedStops, 0, sortedStops.size() - 1, leftBound);
    }

    public static int maxNearestRight(List<Coordinate> sortedStops, Coordinate metro) {
        final int rightBound = metro.x + MAX_DIST;
        return maxNearestRight(sortedStops, 0, sortedStops.size() - 1, rightBound);
    }

    private static long getDistSquared(Coordinate metro, Coordinate busStop) {
        final long xDif = metro.x - busStop.x;
        final long yDif = metro.y - busStop.y;
        return (xDif * xDif + yDif * yDif);
    }

    private static List<Coordinate> readList(BufferedReader reader, int size) throws IOException {
        final List<Coordinate> lst = new ArrayList<Coordinate>(size);
        for (int i = 0; i < size; i++) {
            lst.add(new Coordinate(reader.readLine()));
        }
        return lst;
    }

     public static class Coordinate {
        private final int x;
        private final int y;

        public Coordinate(String line) {
            final String[] split = line.split(" ");
            this.x = Integer.parseInt(split[0]);
            this.y = Integer.parseInt(split[1]);
        }

        private int x() {
            return x;
        }

        private int y() {
            return y;
        }
    }
}


