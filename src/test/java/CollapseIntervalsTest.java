import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CollapseIntervalsTest {
    @Test
    void shouldMerge() {
        var lst = Stream.of(
                        "7 8",
                        "7 8",
                        "2 3",
                        "6 10"
                )
                .map(Interval::new)
                .collect(Collectors.toList());
        var actual = CollapseIntervals.collapseIntervals(lst).stream().map(Interval::format).collect(Collectors.joining(","));
        var expected = "2 3,6 10";
        assertEquals(expected, actual);
    }
}