import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DequeTaskTest {
    @Test
    void shouldExecute1() {
        var capacity = 4;
        var lst = List.of("push_front 861",
                "push_front -819",
                "pop_back",
                "pop_back");
        var expected = "861\n-819";

        assertEquals(expected, DequeTask.executeCommands(capacity, lst).strip());
    }

    @Test
    void shouldExecute2() {
        var capacity = 10;
        var lst = List.of(
                "push_front -855",
                "push_front 0",
                "pop_back",
                "pop_back",
                "push_back 844",
                "pop_back",
                "push_back 823"
        );
        var expected = "-855\n0\n844";

        assertEquals(expected, DequeTask.executeCommands(capacity, lst).strip());
    }
}