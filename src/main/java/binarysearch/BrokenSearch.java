package binarysearch;/*
    Принцип работы.

    Тот же бинарный поиск, только другой метод определения подмассива. В бинарном поиске мы сравниваем со средним элементом
    и есле меньше ищем слева а иначе справа. Тут же мы проверяем является ли массив слева отсортированным, если да, то это обычный бинарный поиск,
    а если массив не отсортирован, то проверяем принадлежит ли искомый элемент смещенному массиву(В таком случае искомый элемент должен быть
    меньше последнего элемента или больше первого)

    Временная сложность.

    O(log(N)) - Время работы такое же как и у обычного бинарного поиска

    Пространственная сложность.

    O(log(N)) - Затраты на рекурсию
 */

// ID = 69032886
public class BrokenSearch {
    public static int brokenSearch(int[] arr, int k) {
        return search(arr, 0, arr.length, k);
    }

    public static int search(int[] arr, int low, int hi, int k) {
        if (low < 0 || low >= hi || low >= arr.length) {
            return -1;
        }


       final int mid = (low + hi) / 2;
        if (arr[mid] == k) {
            return mid;
        }
        if (isBelongToIncreasingRange(arr, low, mid + 1, k) || isBelongToSplitRange(arr, low, mid + 1, k)) {
            return search(arr, low, mid, k);
        }
        return search(arr, mid + 1, hi, k);
    }

    private static boolean isBelongToSplitRange(int[] arr, int low, int hi, int k) {
        return !isIncreasing(arr, low, hi) && (k > arr[low] || k < arr[hi - 1]);
    }

    private static boolean isBelongToIncreasingRange(int[] arr, int low, int hi, int k) {
        return isIncreasing(arr, low, hi) && (k >= arr[low] && k <= arr[hi - 1]);
    }

    private static boolean isIncreasing(int[] arr, int low, int hi) {
        return arr[low] <= arr[hi - 1];
    }
}
