package base;

import org.junit.jupiter.api.Test;
import other.QuickSort;
import tree.HeapSort;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {
    @Test
    void shouldSort() {
        final var lst = new LinkedList<>(List.of(
                HeapSort.Participant.of("tufhdbi 76 58"),
                HeapSort.Participant.of("rqyoazgbmv 59 78"),
                HeapSort.Participant.of("qvgtrlkmyrm 35 27"),
                HeapSort.Participant.of("tgcytmfpj 70 27"),
                HeapSort.Participant.of("xvf 84 19"),
                HeapSort.Participant.of("jzpnpgpcqbsmczrgvsu 30 3"),
                HeapSort.Participant.of("evjphqnevjqakze 92 15"),
                HeapSort.Participant.of("wwzwv 87 8"),
                HeapSort.Participant.of("tfpiqpwmkkduhcupp 1 82"),
                HeapSort.Participant.of("tzamkyqadmybky 5 81"),
                HeapSort.Participant.of("amotrxgba 0 6"),
                HeapSort.Participant.of("easfsifbzkfezn 100 28"),
                HeapSort.Participant.of("kivdiy 70 47")
        ));
        final var expected = new LinkedList<>(List.of(
                "easfsifbzkfezn",
                "evjphqnevjqakze",
                "wwzwv",
                "xvf",
                "tufhdbi",
                "tgcytmfpj",
                "kivdiy",
                "rqyoazgbmv",
                "qvgtrlkmyrm",
                "jzpnpgpcqbsmczrgvsu",
                "tzamkyqadmybky",
                "tfpiqpwmkkduhcupp",
                "amotrxgba"
        )).toArray();

        QuickSort.sort(lst);

        // 2 1 4 5 3

        assertArrayEquals(expected, lst.stream().map(HeapSort.Participant::getName).toArray());

    }

    @Test
    void shouldSortInt() {
        final var lst = new LinkedList<Integer>(List.of(
                3, 1, 4, 5, 2
        ));

        QuickSort.sort(lst);

        // 2 1 4 5 3

        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, lst.stream().toArray());

    }

    @Test
    void shouldCompare1() {
        final var left = HeapSort.Participant.of("gena 6 1000");
        final var right = HeapSort.Participant.of("rita 2 90");
        assertEquals(-1, left.compareTo(right));
        assertEquals(1, right.compareTo(left));

    }

    @Test
    void shouldCompare2() {
        final var gena = HeapSort.Participant.of("gena 6 1000");
        final var rita = HeapSort.Participant.of("rita 6 90");
        assertEquals(1, gena.compareTo(rita));
        assertEquals(-1, rita.compareTo(gena));
    }

    @Test
    void shouldCompare3() {
        final var gena = HeapSort.Participant.of("gena 6 90");
        final var rita = HeapSort.Participant.of("rita 6 90");
        assertTrue(gena.compareTo(rita) < 0);
        assertTrue(rita.compareTo(gena) > 0);
    }

    @Test
    void randomTest() throws Exception {
        final var lst = generateRandom(1, 3000);
        final var copy = new ArrayList<Integer>(lst.size());
        copy.addAll(lst);

        Collections.sort(copy);
        QuickSort.sort(lst);

        assertArrayEquals(copy.toArray(), lst.toArray());
    }

    private List<Integer> generateRandom(int minSize, int maxSize) {
        final int randomNum = ThreadLocalRandom.current().nextInt(minSize, maxSize + 1);
        final var lst = new ArrayList<Integer>();
        for (int i = 0; i < randomNum; i++) {
            lst.add(ThreadLocalRandom.current().nextInt(1, 1_000_000));
        }
        return lst;
    }
}