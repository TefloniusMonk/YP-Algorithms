import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.*;
import java.io.IOException;

/*
    Принцип работы.

    Считаем для каждой клавиши от 1 до 9 их кол-во. Потом для каждого типа клавиш(Цифр на клавишах)
    проверяем возможно ли нажать на все одновременно.

    Временная сложность.

    O(1) - Т.к. размер входных данных строго задан.

    Пространственная сложность.

    O(1) - Т.к. размер входных данных строго задан.
 */


// ID = 68956740
public class B {

    private static Integer getScore(List<List<Integer>> matrix,
                                    int keysPerPerson, int personCount) {
        final var countByNum = groupByCount(matrix);

        final var maxKeys = keysPerPerson * personCount;
        var result = 0;
        for (int digit = 1; digit < 10; digit++) {
            if (countByNum.containsKey(digit) && countByNum.get(digit) <= maxKeys) {
                result++;
            }
        }
        return result;
    }

    private static Map<Integer, Integer> groupByCount(List<List<Integer>> matrix) {
        final var countByNum = new HashMap<Integer, Integer>();

        for (var row : matrix) {
            for (var num : row) {
                countByNum.put(num, countByNum.getOrDefault(num, 0) + 1);
            }
        }
        return countByNum;
    }

    public static void main(String[] args) throws IOException {
        try (final var reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int keysPerPerson = readInt(reader);
            final int personCount = 2; // two boys
            final int rowsCount = 4;
            final var matrix = readMatrix(reader, rowsCount);

            System.out.println(getScore(matrix, keysPerPerson, personCount));
        }
    }


    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(""))
                .map(elem -> elem.equals(".") ? -1 : Integer.parseInt(elem))
                .collect(Collectors.toList());
    }

    private static List<List<Integer>> readMatrix(BufferedReader reader, int rowsCount) throws IOException {
        final List<List<Integer>> matrix = new ArrayList<>(rowsCount);
        for (int i = 0; i < rowsCount; i++) {
            matrix.add(readList(reader));
        }
        return matrix;
    }
}