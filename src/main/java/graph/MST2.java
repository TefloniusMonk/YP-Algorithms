package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.String.format;
/*
    Принцип работы.

    Считываем граф в список ребер т.к. остовное дерево будем строить по алгоритму Краскала.
    P.S. Грани хранятся как массив из 3х short вида : [from, to, weight] т.к. долго не мог пройти по лимиты памяти и пытался все соптимизировать)

    Добавляем все ребра в maxHeap (Т.е. на вершине будет ребро с самым большим весом).
    Создаем массив для хранения цвета(цветов будет столько же сколько вершин) и раскрашиваем каждую вершину в новый цвет.
    Теперь берем максимальное ребро из кучи и если оно соединяет две разных компоненты свзяности(У вершин разные цвета),
    то добавляем ребро и перекрашиваем все вершины в цвет меньшего значения.

    Временная сложность.

    O(E * log(E) + V^2) - создание кучи работает за O(E*log(E)) и удаление всех ребер работает за это же время.
    Перекрашивание всех ребер работает за V^2 т.к. всего V перекрашиваний и одна перекраска требует пройти по
    всему массиву цветов (В данном случае у нас это не скажется на производительности т.к. граф почти полный и V^2 ~ E)
    Итого: 2 * E*log(E) + V^2 => O(E * log(E) + V^2)

    UPD:
        "Вообще тут V^2 можно побороть: нас ведь интересуют лишь ребра из дерева наружу, не важно, какая там по цвету компонента
        То есть достаем ребра по возрастанию веса, если оно ведет из вершины текущего graph.MST в вершину вне дерева, то добавляем в ответ ребро
        Получим ElogE честный"
    Мне кажется это про алгоритм Прима, когда мы берем ребро с большим весом не из дерева и добавляем его к дереву.
    Тут мы все ребра добавляем в очередь и инициализируем весь граф как несвязный граф с V компонент связности(т.е. в нем в начале нет никаких ребер)
    Потом мы берем самое большое ребро и соединяем им две вершины(Сливаем две компоненты связности в одну),
    и потом по одному выбираем ребро и добавляем его в граф только в том случае, если это ребро соединяем разные компоненты связности.
    В итоге мы должны получить один связный граф цвета 1 (Т.к. мы перекрашиваем граф в цвет меньшего значения).
    Возможно, есть способ перекрашивать вершины быстрее чем за O(V^2),но т.к. в данном случае вершин у нас гораздо меньше
    чем ребер и это не сильно сказывает на производительности(Плюс мне приходилось оптимизировать алгоритм по памяти,
    а не по времени из-за лимитов Я.Контеста)

    Пространственная сложность.

    O(E + V) - список ребер занимает O(E) памяти и список цветов занимает O(V)
 */

// ID = 69421986
public class MST2 {
    private static final String OOPS = "Oops! I did it again";

    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final String[] firstLine = reader.readLine().split(" ");
            final int vertexes = Integer.parseInt(firstLine[0]);
            if (vertexes == 1) {
                return "0";
            }
            final int edgesCount = Integer.parseInt(firstLine[1]);
            final PriorityQueue<Short[]> edgeHeap = new PriorityQueue<>(Comparator.comparing(arr -> ((Short[]) arr)[2]).reversed());
            for (int i = 0; i < edgesCount; i++) {
                final Short[] edge = Arrays.stream(reader.readLine().split(" "))
                        .map(Short::parseShort)
                        .toArray(Short[]::new);
                if (!Objects.equals(edge[0], edge[1])) {
                    edgeHeap.add(edge);
                }
            }
            final int[] color = new int[vertexes + 1];
            for (short i = 1; i <= vertexes; i++) {
                color[i] = i;
            }
            int visitedCount = 0;
            int weightSum = 0;
            while (visitedCount != vertexes - 1) {
                if (edgeHeap.isEmpty()) {
                    break;
                }
                final Short[] edge = edgeHeap.poll();
                if (color[edge[0]] == color[edge[1]]) { // same connectivity
                    continue;
                }
                visitedCount++;
                weightSum += edge[2];
                repaint(color, edge);
            }
            if (visitedCount != vertexes - 1) {
                return OOPS;
            }
            return String.valueOf(weightSum);
        }
    }

    private static void repaint(int[] color, Short[] edge) {
        final int maxColor = Math.max(color[edge[0]], color[edge[1]]);
        final int minColor = Math.min(color[edge[0]], color[edge[1]]);
        for(int i = 1; i < color.length; i++){
            if (color[i] == maxColor){
                color[i] = minColor;
            }
        }
    }
}
