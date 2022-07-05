import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
    Принцип работы.

    При создании кучи по одному добавляем элементы и применяем функцию просеивания вверх.
    Потом по одному вытаскиваем самый верхний элемент из кучи, меняем его местами с самым последним в массиве,
    уменьшаем размер массива и применяем просеивание вниз для самого верхнего элемента.

    Временная сложность.

    O(N*log(N)) -  В лучшем, в среднем и в худшем случае.

    Пространственная сложность.

    O(N) - Все элементы копируются в массив кучи
 */

// ID = 69273932
public class HeapSort {
    public static <T extends Comparable<T>> void sort(List<T> list) {
        if (list.size() <= 1) {
            return;
        }
        final var heap = new MinHeap<>(list);
        list.replaceAll(ignored -> heap.pollFirst());
    }

    public static void main(String[] args) throws IOException {
        try (final var reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int size = Integer.parseInt(reader.readLine());
            final var participants = readList(reader, size);
            sort(participants);
            participants.forEach(participant -> System.out.println(participant.getName()));
        }
    }

    private static List<Participant> readList(BufferedReader reader, int size) throws IOException {
        final var lst = new ArrayList<Participant>(size);
        for (int j = 0; j < size; j++) {
            lst.add(Participant.of(reader.readLine()));
        }
        return lst;
    }

    @SuppressWarnings("unchecked")
    static class MinHeap<T extends Comparable<T>> {
        private final T[] heap;
        private int lastIdx;

        public MinHeap(Collection<T> collection) {
            if (collection.size() == 0) {
                throw new IllegalArgumentException("Collection must not be empty");
            }
            heap = (T[]) Array.newInstance(collection.iterator().next().getClass(), collection.size());
            final var iter = collection.iterator();
            for (int i = 0; i < heap.length; i++) {
                heap[i] = iter.next();
                siftUp(i);
            }
            lastIdx = heap.length - 1;
        }

        public T[] getHeap() {
            return heap;
        }

        public T pollFirst() {
            if (lastIdx == -1) {
                throw new IllegalArgumentException("Heap is empty");
            }
            final var first = heap[0];
            heap[0] = heap[lastIdx--];
            siftDown(0);
            return first;
        }

        private void siftUp(int idx) {
            if (idx == 0) {
                return;
            }
            final int parent = idx / 2;
            if (heap[parent].compareTo(heap[idx]) > 0) {
                swap(parent, idx);
                siftUp(parent);
            }
        }

        private void siftDown(int idx) {
            final int left = 2 * idx;
            final int right = left + 1;

            if (lastIdx <= left) {
                return;
            }

            int smallestIdx = left;
            if (right <= lastIdx && heap[left].compareTo(heap[right]) > 0) {
                smallestIdx = right;
            }
            if (heap[idx].compareTo(heap[smallestIdx]) > 0) {
                swap(idx, smallestIdx);
                siftDown(smallestIdx);
            }
        }

        private void swap(int idx1, int idx2) {
            final T t = heap[idx1];
            heap[idx1] = heap[idx2];
            heap[idx2] = t;
        }
    }

    static class Participant implements Comparable<Participant> {
        private final String name;
        private final int solved;
        private final int penalty;

        private Participant(String name, int solved, int penalty) {
            this.name = name;
            this.solved = solved;
            this.penalty = penalty;
        }

        public static Participant of(String line) {
            final var split = line.split(" ");
            return new Participant(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        }

        public String getName() {
            return name;
        }


        @Override
        public int compareTo(Participant other) {
            if (this == other) {
                return 0;
            }
            if (this.solved != other.solved) {
                return this.solved > other.solved ? -1 : 1;
            }
            if (this.penalty != other.penalty) {
                return this.penalty > other.penalty ? 1 : -1;
            }
            return this.name.compareTo(other.name);
        }
    }
}

