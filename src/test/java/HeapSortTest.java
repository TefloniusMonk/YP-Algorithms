import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class HeapSortTest {
    @Test
    void shouldCreateHeap() {
        final var lst = List.of(50, 45, 20, 17, 14, 10, 6, 5, 4, 33);
        final var expected = List.of(4, 5, 6, 10, 17, 20, 45, 14, 50, 33);
        final var heap = new HeapSort.MinHeap<>(lst);
        final var actual = heap.getHeap();
        assertArrayEquals(expected.toArray(), actual);
    }

    @Test
    void shouldRemoveFirstFromHeap() {
        final var lst = List.of(50, 45, 20, 17, 14, 10, 6, 5, 4, 33);
        final var heap = new HeapSort.MinHeap<>(lst);
        final var firstVal = heap.pollFirst();
        assertEquals(4, firstVal);
    }

    @Test
    void shouldSort() {
        final var lst = new LinkedList<>(List.of(
                Participant.of("tufhdbi 76 58"),
                Participant.of("rqyoazgbmv 59 78"),
                Participant.of("qvgtrlkmyrm 35 27"),
                Participant.of("tgcytmfpj 70 27"),
                Participant.of("xvf 84 19"),
                Participant.of("jzpnpgpcqbsmczrgvsu 30 3"),
                Participant.of("evjphqnevjqakze 92 15"),
                Participant.of("wwzwv 87 8"),
                Participant.of("tfpiqpwmkkduhcupp 1 82"),
                Participant.of("tzamkyqadmybky 5 81"),
                Participant.of("amotrxgba 0 6"),
                Participant.of("easfsifbzkfezn 100 28"),
                Participant.of("kivdiy 70 47")
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

        HeapSort.sort(lst);

        // 2 1 4 5 3

        assertArrayEquals(expected, lst.stream().map(Participant::getName).toArray());

    }

    @Test
    void shouldSortInt() {
        final var lst = new LinkedList<Integer>(List.of(
                3, 1, 4, 5, 2, 6, 9, 8, 0
        ));

        HeapSort.sort(lst);

        // 2 1 4 5 3

        assertArrayEquals(new Integer[]{0, 1, 2, 3, 4, 5, 6, 8, 9}, lst.stream().toArray());

    }

    @Test
    void shouldCompare1() {
        final var left = Participant.of("gena 6 1000");
        final var right = Participant.of("rita 2 90");
        assertEquals(-1, left.compareTo(right));
        assertEquals(1, right.compareTo(left));

    }

    @Test
    void shouldCompare2() {
        final var gena = Participant.of("gena 6 1000");
        final var rita = Participant.of("rita 6 90");
        assertEquals(1, gena.compareTo(rita));
        assertEquals(-1, rita.compareTo(gena));
    }

    @Test
    void shouldCompare3() {
        final var gena = Participant.of("gena 6 90");
        final var rita = Participant.of("rita 6 90");
        assertTrue(gena.compareTo(rita) < 0);
        assertTrue(rita.compareTo(gena) > 0);
    }

    @Test
    void randomTest() {
        final var lst = generateRandom(1, 3000);
        final var copy = new ArrayList<Integer>(lst.size());
        copy.addAll(lst);

        Collections.sort(copy);
        HeapSort.sort(lst);

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