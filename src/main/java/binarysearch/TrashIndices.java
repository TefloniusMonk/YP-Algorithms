package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Math.abs;

public class TrashIndices {
    public static int findKthMin(int[] arr, int k) {
        Arrays.sort(arr);
        int low = findMinDif(arr);
        int hi = abs(arr[arr.length - 1] - arr[0]);
        while (low <= hi) {
            final int mid = (low + hi) / 2;
            final long countOfPairsLessOrEq = countPairWithDiffLessOrEq(arr,mid);
            if (countOfPairsLessOrEq < k) {
                low = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return low;
    }

    private static long countPairWithDiffLessOrEq(int[] arr, int mid) {
        long res = 0;
        for (int i = 0; i < arr.length; i++) {
            res += upperBound(arr, i, arr[i] + mid) - i - 1;
        }
        return res;
    }

    private static int upperBound(int[] arr, int start, int val) {
        int hi = arr.length -1;
        while (start <= hi){
            final int mid = (start + hi) / 2;
            if (val >= arr[mid]){
                start = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return start;
    }

    private static int findMinDif(int[] arr) {
        int min = Integer.MAX_VALUE;
        int i = 0;
        int j = 1;
        while (j < arr.length) {
            final int dif = abs(arr[j] - arr[i]);
            if (dif < min) {
                min = dif;
            }
            i++;
            j++;
        }
        return min;
    }

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int size = Integer.parseInt(reader.readLine());
            final int[] arr = readList(reader, size);
            final int k = Integer.parseInt(reader.readLine());
            System.out.println(findKthMin(arr, k));
        }
    }

    private static int[] readList(BufferedReader reader, int size) throws IOException {
        final int[] arr = new int[size];
        int i = 0;
        final StringTokenizer st = new StringTokenizer(reader.readLine());
        while (st.hasMoreTokens()) {
            arr[i] = Integer.parseInt(st.nextToken());
            i++;
        }
        return arr;
    }
}