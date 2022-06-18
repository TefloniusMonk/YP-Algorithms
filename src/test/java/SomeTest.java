import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SomeTest {

    private static int find(List<Integer> money, int cost) {
        if (money.size() == 0) return -1;
        if (money.size() == 1) return money.get(0) >= cost ? 0 : -1;
        return search(money, 0, money.size() - 1, cost);
    }

    private static int search(List<Integer> lst, int startIncluded, int endIncluded, int val) {
        if (startIncluded == endIncluded && lst.get(startIncluded) >= val) return startIncluded;
        if (startIncluded >= endIncluded) return -1;

        var pivotIdx = (startIncluded + endIncluded) / 2;
        var pivot = lst.get(pivotIdx);
        if (val <= pivot) {
            return search(lst, startIncluded, pivotIdx, val);
        }
        return search(lst, pivotIdx + 1, endIncluded, val);
    }

    public static void main(String[] args) throws IOException {
        try (final var reader = new BufferedReader(new InputStreamReader(System.in))) {
            reader.readLine();
            final var moneyByDays = readList(reader);
            final int cost = readInt(reader);
            final String result = getResult(moneyByDays, cost);
            System.out.println(result);
        }
    }

    private static String getResult(List<Integer> moneyByDays, int cost) {
        final int firstDay = fromIdxToDay(find(moneyByDays, cost));
        final int secondDay = fromIdxToDay(find(moneyByDays, cost * 2));
        final var result = firstDay + " " + secondDay;
        return result;
    }

    private static int fromIdxToDay(int idx) {
        return idx == -1 ? -1 : idx + 1;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        var lst = new ArrayList<Integer>();
        var st = new StringTokenizer(reader.readLine());
        while (st.hasMoreTokens()) {
            lst.add(Integer.parseInt(st.nextToken()));
        }
        return lst;
    }

    @Test
    void test() {
        var lst = List.of(1, 2, 4, 4, 4, 4);
        var cost = 3;
        var expected = "3 -1";
        var actual = getResult(lst, cost);
        Assertions.assertEquals(expected, actual);
    }
}
