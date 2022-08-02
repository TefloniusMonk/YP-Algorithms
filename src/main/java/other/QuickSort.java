package other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.swap;


/*
    Принцип работы.

    Считываем строки, маппим в объект other.Participant. other.Participant реализует интерфейс Comparable,
    так что его можно использовать для сравнения(Это удобно для отдельного тестирования методов сравнения и самого алгоритма сортировки).

    Временная сложность.

    O(N*log(N)) -  В среднем и в лучшем случае. N^2 в худшем(В данном случае в качестве опорного эл-та выбирается средний,
    если средний элемент всегда будет меньшим или большим на отрезке тогда скорость деградирует до N^2)

    Пространственная сложность.

    O(log(N)) - Затраты на рекурсию
 */

// ID = 69036198
public class QuickSort {
    public static <T extends Comparable<T>> void sort(List<T> list) {
        // Не понимаю, что плохого в однострочный if. На мой взгляд, с ними код короче и легче читается.
        // Когда надо обработать крайний случай это будет занимать в три раза меньше строк. Так что, на мой взгляд, это вкусовщина.
        // То же самое с final, к сожалению, в джаву не завезли еще val, а везде писать final var выглядит как Ъ в дореволюционном русском,
        // кроме тех случав где в этом действительно есть смысл, например для иммутабельности объектов.
        // Так что такие вещи обычно устанавливаются в рамках проекта и чекстайла и где-то может быть приемлемо и даже нужно писать if в одну строчку.
        // Возможно, стоит тогда ввести в курсе styleguide по языкам.
        if (list.size() <= 1) {
            return;
        }
        quickSort(list, 0, list.size());
    }

    private static <T extends Comparable<T>> void quickSort(List<T> list, int low, int high) {
        if (low >= high - 1) {
            return;
        }

        final var pivot = partition(list, low, high - 1);

        quickSort(list, low, pivot);
        quickSort(list, pivot, high);
    }

    public static <T extends Comparable<T>> int partition(List<T> list, int left, int right) {
        final var pivotVal = list.get((left + right) / 2);
        while (left <= right) {
            while (list.get(left).compareTo(pivotVal) < 0) {
                left++;
            }
            while (list.get(right).compareTo(pivotVal) > 0) {
                right--;
            }
            if (left <= right) {
                swap(list, left++, right--);
            }
        }
        return left;
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


    public static class Participant implements Comparable<Participant> {
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
