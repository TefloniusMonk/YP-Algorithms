import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/*
    Принцип работы.

    Создаем массив объектов Bucket. В каждом bucket хранится связный список из Entry.
     Т.к. по условию масштабирование не нужно, то размер выбирался по кол-ву инструкций.
    Разрешение коллизий реализовано с помощью метода цепочек: сначала по ключу вычисляем хэш, берем остаток по модулю
    и используем как индекс в таблице, а потом ищем через equals по связному списку.

    Временная сложность.

    O(1) - В среднем и в лучшем случае. В худшем случае O(N), хотя при Bucket[].length ~ N это крайне маловероятно.
    UPD в данном случае все бакеты будут из одного элемента, т.к. мы используем int как ключ,
    а Objects.hash() всегда возвращает int + 31 и значит два разных числа не могут попасть в один бакет

    Пространственная сложность.

    O(N) - В данном случае мы имеем массив константного размера + сами значения key, value и память на объекты Entry и Node в LinkedList

    P.S. На счет if-ов и final настроил себе IDEA на автозамену :)
 */

// ID = 69067083
public class CustomMapTask {
    private static final String NONE = "None";

    public static void main(String[] args) throws IOException {
        final var result = inputWrapper(System.in);
        System.out.println(result);
    }

    public static String inputWrapper(InputStream inputStream) throws IOException {
        try (final var reader = new BufferedReader(new InputStreamReader(inputStream))) {
            final int size = Integer.parseInt(reader.readLine());
            final var lstOfCommands = readList(reader, size);
            final var builder = new StringBuilder();
            final var map = new CustomMapImpl(size);
            for (var command : lstOfCommands) {
                final var output = executeCommand(map, command);
                if (output == null) {
                    continue;
                }
                if (output.equals(CustomMap.NOT_FOUND)) {
                    builder.append(NONE);
                } else {
                    builder.append(output);
                }
                builder.append("\n");
            }
            return builder.toString().strip();
        }
    }

    private static Integer executeCommand(CustomMap map, String command) {
        final var split = command.split(" ");
        switch (split[0]) {
            case "get":
                return map.get(Integer.parseInt(split[1]));
            case "put":
                map.put(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
                return null;
            case "delete":
                return map.delete(Integer.parseInt(split[1]));
            default:
                return null;
        }
    }

    private static List<String> readList(BufferedReader reader, int size) throws IOException {
        final var lst = new ArrayList<String>(size);
        for (int j = 0; j < size; j++) {
            lst.add(reader.readLine());
        }
        return lst;
    }

}

interface CustomMap {
    Integer NOT_FOUND = -1;

    void put(Integer key, Integer value);

    Integer get(Integer key);

    Integer delete(Integer key);
}

class CustomMapImpl implements CustomMap {
    private static final double ALPHA = 0.75;
    private static final Integer MAX_CAPACITY = (int) Math.ceil(ALPHA * Math.pow(10, 5));
    private final Bucket[] buckets;

    public CustomMapImpl() {
        buckets = new Bucket[MAX_CAPACITY];
    }

    public CustomMapImpl(int capacity) {
        buckets = new Bucket[(int)(capacity * ALPHA)];
    }

    @Override
    public void put(Integer key, Integer value) {
        final var bucketId = Objects.hash(key) % buckets.length; // Для key > 0 && key < 10^5 Objects.hash(key) всегда больше нуля
        final var bucket = buckets[bucketId];
        if (bucket == null) {
            buckets[bucketId] = Bucket.of(key, value);
        } else {
            bucket.replaceOrCreate(key, value);
        }
    }

    @Override
    public Integer get(Integer key) {
        return getBucket(key)
                .flatMap(it -> it.find(key))
                .map(it -> it.value)
                .orElse(NOT_FOUND);
    }

    @Override
    public Integer delete(Integer key) {
        return getBucket(key)
                .map(bucket -> removeByIndex(bucket, key))
                .orElse(NOT_FOUND);
    }

    private Integer removeByIndex(Bucket bucket, Integer key) {
        for (Entry entry : bucket.entries) {
            if (entry.key.equals(key)) {
                bucket.entries.remove(entry);
                return entry.value;
            }
        }
        return NOT_FOUND;
    }

    private Optional<Bucket> getBucket(Integer key) {
        return Optional.ofNullable(buckets[Objects.hash(key) % buckets.length]);
    }

    static class Bucket {
        private final LinkedList<Entry> entries = new LinkedList<>();

        private Bucket(Entry entry) {
            entries.addFirst(entry);
        }

        public void add(Integer key, Integer value) {
            entries.offerFirst(Entry.of(key, value));
        }

        public Optional<Entry> find(Integer key) {
            return entries.stream()
                    .filter(entry -> Objects.equals(key, entry.key))
                    .findFirst();
        }

        public static Bucket of(Integer key, Integer value) {
            return new Bucket(Entry.of(key, value));
        }

        private void replaceOrCreate(Integer key, Integer value) {
            final boolean isPresent = entries.stream()
                    .filter(entry -> entry.key.equals(key))
                    .peek(it -> it.value = value)
                    .findFirst()
                    .isPresent();
            if (!isPresent) {
                entries.offerFirst(Entry.of(key, value));
            }
        }
    }

    static class Entry {
        private final Integer key;
        private Integer value;

        private Entry(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public static Entry of(Integer key, Integer value) {
            return new Entry(key, value);
        }
    }
}


