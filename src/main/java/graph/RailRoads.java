package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
/*
    Принцип работы.

    Считываем граф в список смежности, и при чтении дороги одного из типов разворачиваем.
    В получившемся ориентированном графе ищем циклы. Если хотя бы один есть, то графи не "оптимальный".
    Проверка на цикл следующая:
        1) Создаем массив цветов вершин и помечаем все вершины как "Белая".
        2) Для каждой вершины запускаем обход в глубину:
            Для каждой вершины создаем очередь и добавляем туда вершину.
            Запускаем цикл пока очередь не пустая и добавляем туда смежные "белые" вершины,
            если в списке смежности лежит "серая" вершина, значит мы нашли цикл и граф не оптимальный.
        3) Граф оптимальный.

    Временная сложность.

    O(V + E) - Внешний цикл проходит по каждой вершине один раз и внутренний цикл(обход в глубину) проходит по каждому ребру
        Т.е. если мы на первой итерации пройдем по всему графу, то все веришины будут помечены как не "белые"
        и значит внешний цикл дойдет до конца и больше не будет запускать обход в глубину

    Пространственная сложность.

    O(E + V^2) - Список смежности будет занимать O(V^2) / 2 (Можно представить в виде квадратной матрицы,
    где диагональ и все значения ниже диагонали нули). Очередь в худшем случае будет содержать все вершины, т.е. O(E)

    UPD:
    В общем случае памяти понадобится E + V, но нам же заранее известно соотношение кол-ва ребер и вершин по условию,
    например для 6 вершин матрица будет выглядеть так:
           0 * * * * *
           0 0 * * * *
           0 0 0 * * *
           0 0 0 0 * *
           0 0 0 0 0 *
           0 0 0 0 0 0
    А значит кол-во вершин всегда будет: { (v - 1) + (v - 2) + (v - 3) ... (v - v) } =>
           { (V ^ 2) / 2  - V }  ("-V" для учета диагонали)
    Распишем всю необходимую память:
       1) Т.к. для каждой вершины мы создаем массив размера V, в нем будет ссылка на массив смежных ребер(В общем случае размера V)
           Т.е. список смежности будет занимать V^2.
       2) Для хранения цвета вершины массив размера V.
       3) Очередь в худшем случае будет хранить все вершины, т.е. ее размер будет V^2
    Итого: V^2 + V + V^2 = 2 * V^2 + V => O(V^2) (так что да, в начальной оценке я ошибся)

    Если расписывать в общем случае, то кол-во ребер может быть любым и тогда у нас будет мапа размера V + E,
    массив цветов размера V и очередь размера E => E + V + V + E => 2(E + V) => O(E+V)

    В данной задаче O(E+V) ~ O(V^2) т.к: E ~ V^2 => O(E + V) ~ O(V^2 + V), а O(V^2 + V) это просто O(V^2)

 */

// ID = 69411684
public class RailRoads {
    private static final String YES = "YES";
    private static final String NO = "NO";
    private static final short WHITE = 0;
    private static final short GRAY = 1;
    private static final short BLACK = 2;

    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final String[] firstLine = reader.readLine().split(" ");
            final int cities = Integer.parseInt(firstLine[0]);
            if (cities == 1) {
                return YES;
            }

            final Map<Integer, List<Integer>> graph = new HashMap<>(cities);
            for (int i = 1; i <= cities; i++) {
                graph.put(i, new ArrayList<>());
            }
            for (int i = 1; i < cities; i++) {
                final String roads = reader.readLine();
                final List<Integer> set = graph.get(i);
                for (int r = 1; r <= roads.length(); r++) {
                    if (roads.charAt(r - 1) == 'B') {
                        set.add(i + r);
                    } else {
                        graph.get(i + r).add(i);
                    }
                }
            }

            final short[] color = new short[cities + 1];

            for (int i = 1; i <= cities; i++) {
                if (color[i] != WHITE) {
                    continue;
                }
                final Deque<Integer> deque = new LinkedList<>();
                deque.push(i);
                while (!deque.isEmpty()) {
                    final Integer node = deque.pop();
                    if (color[node] == BLACK) {
                        continue;
                    }
                    if (color[node] == GRAY) {
                        color[node] = BLACK;
                        continue;
                    }
                    color[node] = GRAY;
                    deque.addFirst(node);
                    for (Integer v : graph.get(node)) {
                        if (color[v] == WHITE) {
                            deque.addFirst(v);
                        }
                        if (color[v] == GRAY) {
                            return NO;
                        }
                    }

                }
            }
            return YES;
        }

    }
}
