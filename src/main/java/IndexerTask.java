import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
    === Принцип работы.

        Создаем индекс по документам. Для этого разбиваем каждый документ на слова и каждое слово кладем в хэш-таблицу,
    где ключом будет слово, а значением - список индексов документов. Потом, каждый запрос мы разбиваем на слова,
    каждое слово учитываем только один раз, для каждого слова получаем в таблице список документов(Если такие есть)
    и группируем документы по кол-ву вхождений. Потом сортируем по кол-ву и по номеру индекса и берем первые 5.
        Так же, в данном решении был использован грязный хак в виде кэша запросов(Что кажется крайне логичным решением для поисковой системы :)).


    === Временная сложность.

    O(N*M + K * J * N * log(N)) или N * (M + K * J * log(N)) - где:
        N - число документов,
        M число строк в документе.
        K число запросов,
        J число слов в запросе,
    Создание индекса происходит за O(N*M) где N - число документов, а M число строк в документе.
    Генерация ответов работает за O(K * J * N * log(N)) (o_O), где:

        В худшем случае каждое слово запроса будет содержаться в каждом документе,
    в результате таблица, содержащая группировку по (idx, count(idx)) будет иметь длину N(все документы)
    и нам придется эту таблицу отсортировать.

    === Пространственная сложность.

    O(N * M + K) - На индекс требуется O(N*M) где N - число документов, а M число строк в документе
    (Это худший случай когда в каждом документе уникальные слова).
    Кэш требует O(K), где K число запросов(Ответ имеет фиксированный размер не более 5 Integer, так что считаем его константным для одного запроса).
    Кэш можно сделать и буфферизованным, т.к. нет смысла хранить все запросы.
 */

// ID = 69075419
public class IndexerTask {

    public static void main(String[] args) throws IOException {
        final var result = inputWrapper(System.in);
        System.out.println(result);
    }

    public static String inputWrapper(InputStream inputStream) throws IOException {
        try (final var reader = new BufferedReader(new InputStreamReader(inputStream))) {
            final int size = Integer.parseInt(reader.readLine());
            final var documents = readList(reader, size);
            final int sizeOfRequests = Integer.parseInt(reader.readLine());
            final var requests = readList(reader, sizeOfRequests);
            final var indexer = new Indexer(documents);
            final var indices = findMostRelevant(indexer, requests);

            final var builder = new StringBuilder();
            for (var top5 : indices) {
                builder.append(top5.stream().map(String::valueOf).collect(Collectors.joining(" ")))
                        .append("\n");
            }
            return builder.toString().strip();
        }
    }

    private static List<List<Integer>> findMostRelevant(Indexer indexer, List<String> requests) {
        return requests.stream()
                .map(indexer::topRelevant)
                .collect(Collectors.toList());
    }

    private static List<String> readList(BufferedReader reader, int size) throws IOException {
        final var lst = new ArrayList<String>(size);
        for (int j = 0; j < size; j++) {
            lst.add(reader.readLine());
        }
        return lst;
    }
}

class Indexer {
    private static final Integer TOP_K_RELEVANT = 5;
    private final Map<String, List<Integer>> index = new HashMap<>();
    private final Map<String, List<Integer>> cache = new HashMap<>();

    public Indexer(List<String> documents) {
        int i = 1;
        for (var document : documents) {
            final var tokenizer = new StringTokenizer(document);
            while (tokenizer.hasMoreTokens()) {
                final var token = tokenizer.nextToken();
                final var set = index.getOrDefault(token, new ArrayList<>());
                set.add(i);
                index.put(token, set);
            }
            i++;
        }
    }

    public List<Integer> topRelevant(String request) {
        if (cache.containsKey(request)) {
            return cache.get(request);
        }

        final var result = Arrays.stream(request.split(" "))
                .distinct()                                                         // Убираем дубликаты из запроса
                .filter(index::containsKey)                                         // Отфильтровываем слова которых нет в начальных документах
                .map(index::get)                                                    // Получаем все индексы документов для каждого слова
                .flatMap(Collection::stream)                                        // Мерджим все в один большой стрим
                .collect(Collectors.groupingBy(it -> it, Collectors.counting()))    // Группируем по (idx, count(idx))
                .entrySet().stream()                                                //
                .sorted(this::compare)                                              // сортируем
                .map(Map.Entry::getKey)                                             // берем только индексы
                .limit(TOP_K_RELEVANT)                                              // первые 5
                .collect(Collectors.toList());                                      // Profit

        cache.put(request, result);
        return result;

    }

    private int compare(Map.Entry<Integer, Long> e1, Map.Entry<Integer, Long> e2) {
        if (!e1.getValue().equals(e2.getValue())) {
            return Long.compare(e2.getValue(), e1.getValue());
        } else {
            return Integer.compare(e1.getKey(), e2.getKey());
        }
    }
}


