package base;

import org.junit.jupiter.api.Test;
import other.CollapseIntervals;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CollapseIntervalsTest {
    @Test
    void shouldMerge() {
        final var lst = Stream.of(
                        "7 8",
                        "7 8",
                        "2 3",
                        "6 10"
                )
                .map(CollapseIntervals.Interval::new)
                .collect(Collectors.toList());
        final var actual = CollapseIntervals.collapseIntervals(lst).stream().map(CollapseIntervals.Interval::format).collect(Collectors.joining(","));
        final var expected = "2 3,6 10";
        assertEquals(expected, actual);
    }
}