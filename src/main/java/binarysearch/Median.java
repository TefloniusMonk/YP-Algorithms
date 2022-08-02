package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Median {
    private static double getArrMedian(List<Integer> arr) {
        return ((double) arr.get((arr.size() + 1) / 2)
                + arr.get((arr.size() + 2) / 2)) / 2;
    }

    private static double findMedian(List<Integer> arr1, List<Integer> arr2) {
        if (arr1.size() == 0 && arr2.size() == 0) {
            return 0;
        }
        if (arr2.size() == 0) {
            return getArrMedian(arr1);
        }
        if (arr1.size() == 0) {
            return getArrMedian(arr2);
        }

        final int length = arr1.size() + arr2.size();
        final int left = (length + 1) / 2;
        final int right = (length + 2) / 2;
        return (((double) findK(arr1, 0, arr2, 0, left)) + findK(arr1, 0, arr2, 0, right)) / 2;
    }

    private static int findK(List<Integer> arr1,
                             int start1, List<Integer> arr2, int start2, int k) {
        if (start1 >= arr1.size()) {
            return arr2.get(start2 + k - 1);
        }

        if (start2 >= arr2.size()) {
            return arr1.get(start1 + k - 1);
        }

        if (k == 1) {
            return Math.min(arr1.get(start1), arr2.get(start2));
        }

        final int mid1 = getMiddle(start1, k);
        final int mid2 = getMiddle(start2, k);

        int val1 = Integer.MAX_VALUE, val2 = Integer.MAX_VALUE;

        if (mid1 < arr1.size()) {
            val1 = arr1.get(mid1);
        }
        if (mid2 < arr2.size()) {
            val2 = arr2.get(mid2);
        }

        if (val1 <= val2) {
            return findK(arr1, mid1 + 1, arr2, start2, k - k / 2);
        } else {
            return findK(arr1, start1, arr2, mid2 + 1, k - k / 2);
        }
    }

    private static int getMiddle(int idx, int k) {
        return idx + k / 2 - 1;
    }

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int size1 = Integer.parseInt(reader.readLine());
            final int size2 = Integer.parseInt(reader.readLine());
            final List<Integer> arr1 = readList(reader, size1 + 1000);
            final List<Integer> arr2 = readList(reader, size2 + 1000);
            final double median = findMedian(arr1, arr2);
            System.out.println(median);
        }
    }

    private static List<Integer> readList(BufferedReader reader, int size) throws IOException {
        final List<Integer> lst = new ArrayList<>(size);
        final StringTokenizer st = new StringTokenizer(reader.readLine());
        while (st.hasMoreTokens()) {
            lst.add(Integer.parseInt(st.nextToken()));
        }
        return lst;
    }
}