package base;

import hash.Competition;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CompetitionTest {

    @Test
    void shouldFindMaxDraw1() {
        final var expected = 2;
        final var arr = List.of(-1, 1);
        final var actual = Competition.findMaxDraw(arr);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindMaxDraw2() {
        final var expected = 30;
        final var arr = Arrays.stream("1 1 1 1 1 0 0 1 1 0 1 1 0 0 0 0 1 0 1 0 1 0 0 0 1 0 0 1 1 0 0 0 0 1 0 1 0 1 0 0 1 0 1 0 0 1 0 1 1 0 0 0 1 1 0 1 0 0 0 0 0 0 0 0 0 1 1 0 1 0 0 0 0 0 1 1 0 0 1 1 1 1 1 0 1"
                        .split(" "))
                .map(Integer::parseInt)
                .map(i -> i == 0 ? -1 : 1)
                .collect(Collectors.toList());
        final var actual = Competition.findMaxDraw(arr);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindMaxDraw3() {
        final var expected = 22;
        final var arr = Arrays.stream((
                "0 1 1 1 1 1 0 1 1 0 1 0 1 0 1 0 1 1 0 1 1 1 0 1 0 0 1 0 0 1 0"
                )
                        .split(" "))
                .map(Integer::parseInt)
                .map(i -> i == 0 ? -1 : 1)
                .collect(Collectors.toList());
        final var actual = Competition.findMaxDraw(arr);
        assertEquals(expected, actual);
    }
}