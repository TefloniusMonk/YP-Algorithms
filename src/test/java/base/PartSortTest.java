package base;

import org.junit.jupiter.api.Test;
import other.PartSort;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class PartSortTest {
    @Test
    void shouldOk(){
        final var a = Arrays.stream("0 1 3 2".split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        final var actual = PartSort.countMaxBlocks(a);
        final var expected = 3 ;
        assertEquals(expected, actual);
    }

    @Test
    void shouldOk1(){
        final var a = Arrays.stream("3 6 7 4 1 5 0 2".split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        final var actual = PartSort.countMaxBlocks(a);
        final var expected = 1 ;
        assertEquals(expected, actual);
    }

    @Test
    void shouldOk2(){
        final var a = Arrays.stream("1 0 2 3 4".split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        final var actual = PartSort.countMaxBlocks(a);
        final var expected = 4 ;
        assertEquals(expected, actual);
    }

    @Test
    void shouldOk3(){
        final var a = Arrays.stream("1 0 2 3 4".split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        final var actual = PartSort.countMaxBlocks(a);
        final var expected = 4 ;
        assertEquals(expected, actual);
    }

    @Test
    void shouldOk4(){
        final var a = Arrays.stream("2 6 9 7 1 3 0 5 8 4".split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        final var actual = PartSort.countMaxBlocks(a);
        final var expected = 1 ;
        assertEquals(expected, actual);
    }
}