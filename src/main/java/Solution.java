import java.util.Arrays;

public class Solution {
    public static int[] merge(int[] arr, int left, int mid, int right) {
        int midCopy = mid;
        int[] res = new int[right - left];
        int resIdx = 0;
        while (resIdx < res.length) {
            int l = Integer.MAX_VALUE;
            if (left < midCopy) {
                l = arr[left];
            }
            int r = Integer.MAX_VALUE;
            if (mid < right) {
                r = arr[mid];
            }
            int min;
            if (l < r) {
                min = l;
                left++;
            } else {
                min = r;
                mid++;
            }
            res[resIdx] = min;
            resIdx++;
        }
        return res;
    }

    public static void copy(int[] arr, int start, int[] arr2) {
        for (int j : arr2) {
            arr[start] = j;
            start++;
        }
    }

    public static void merge_sort(int[] arr, int left, int right) {
        if (right - left <= 1) return;

        int middle = left + ((right - left) / 2);
        merge_sort(arr, left, middle);
        merge_sort(arr, middle, right);
        int[] merged = merge(arr, left, middle, right);
        copy(arr, left, merged);
    }

    public static void main(String[] args) {
        int[] a = {-6, -12, -14, 14};
        merge_sort(a, 0, a.length);
        int[] expected = {-14, -12, -6, 14};
        assert Arrays.equals(a, expected);
    }
}