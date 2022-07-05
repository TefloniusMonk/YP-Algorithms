
public class Heap {
    public static int siftDown(int[] heap, int idx) {

        final int left = 2 * idx;
        final int right = left + 1;

        if (heap.length <= left) {
            return idx;
        }

        int largestIdx = left;
        if (right < heap.length && heap[left] < heap[right]) {
            largestIdx = right;
        }
        if (heap[idx] < heap[largestIdx]) {
            swap(heap, idx, largestIdx);
            return siftDown(heap, largestIdx);
        }
        return idx;
    }

    public static int siftUp(int[] heap, int idx) {
        if (idx == 1) {
            return idx;
        }
        final int parent = idx / 2;
        if (heap[parent] < heap[idx]) {
            swap(heap, parent, idx);
            return siftUp(heap, parent);
        }
        return idx;
    }

    private static void swap(int[] x, int a, int b) {
        final int t = x[a];
        x[a] = x[b];
        x[b] = t;
    }
}
