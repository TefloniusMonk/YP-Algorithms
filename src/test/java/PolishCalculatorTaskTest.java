import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PolishCalculatorTaskTest {

    @Test
    void shouldDiv() {
        var lst = List.of("-1", "3", "/");
        var expected = -1;

        var actual = PolishCalculatorTask.compute(lst);

        assertEquals(expected, actual);
    }

    @Test
    void shouldExecute1() {
        var lst = List.of("2", "1", "+", "3", "*");
        var expected = 9;

        var actual = PolishCalculatorTask.compute(lst);

        assertEquals(expected, actual);
    }

    @Test
    void shouldExecute2() {
        var lst = List.of("7", "2", "+", "4", "*", "2", "+");
        var expected = 38;

        var actual = PolishCalculatorTask.compute(lst);

        assertEquals(expected, actual);
    }
}