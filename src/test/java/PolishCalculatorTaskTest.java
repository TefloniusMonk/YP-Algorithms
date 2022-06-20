import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PolishCalculatorTaskTest {

    @Test
    void shouldDiv() {
        final var lst = List.of("-1", "3", "/");
        final var expected = -1;

        final var actual = PolishCalculatorTask.compute(lst);

        assertEquals(expected, actual);
    }

    @Test
    void shouldExecute1() {
        final var lst = List.of("2", "1", "+", "3", "*");
        final var expected = 9;

        final var actual = PolishCalculatorTask.compute(lst);

        assertEquals(expected, actual);
    }

    @Test
    void shouldExecute2() {
        final var lst = List.of("7", "2", "+", "4", "*", "2", "+");
        final var expected = 38;

        final var actual = PolishCalculatorTask.compute(lst);

        assertEquals(expected, actual);
    }
}