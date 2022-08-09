//package other;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class NearestStopsHilbertCurve {
//
//    private static final int MAX_DIST = 20;
//    private static final int MAX_DIST_SQUARED = MAX_DIST * MAX_DIST;
//
//    private static List<Coordinate> readList(BufferedReader reader, int size) throws IOException {
//        final List<Coordinate> lst = new ArrayList<Coordinate>(size);
//        for (int i = 0; i < size; i++) {
//            lst.add(new Coordinate(reader.readLine()));
//        }
//        return lst;
//    }
//
//    public static void main(String[] args) throws IOException {
//        System.out.println(wrap(System.in));
//    }
//
//    public static String wrap(InputStream inputStream) throws IOException {
//        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
//            final int metroCount = Integer.parseInt(reader.readLine());
//            final List<Coordinate> metros = readList(reader, metroCount);
//            final Map<Integer, List<Coordinate>> map = computeHilbertCoordinates(metros);
//            final int busStopsCount = Integer.parseInt(reader.readLine());
//            final List<Coordinate> busStops = readList(reader, busStopsCount);
//            return findMaxNearest(metros, busStops);
//        }
//    }
//
//    private static Map<Integer, List<Coordinate>> computeHilbertCoordinates(List<Coordinate> metros) {
//        final var map = new HashMap<Integer, List<Coordinate>>();
//
//
//    }
//
//    private static String findMaxNearest(List<Coordinate> metros, List<Coordinate> busStops) {
//
//    }
//
//    public static class Coordinate {
//        private final int x;
//        private final int y;
//
//        public Coordinate(String line) {
//            final String[] split = line.split(" ");
//            this.x = Integer.parseInt(split[0]);
//            this.y = Integer.parseInt(split[1]);
//        }
//    }
//}
