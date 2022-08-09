package all;

import base.AbstractInputWrapperTest;
import base.ThrowingFunction;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.randomizers.number.IntegerRandomizer;
import org.junit.jupiter.api.Test;
import org.w3c.dom.ranges.Range;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Code200Test extends AbstractInputWrapperTest {

    @Override
    protected ThrowingFunction<InputStream, Object> testingMethod() {
        return Code200::wrap;
    }

    @Override
    protected String dirname() {
        return "code-200";
    }

    @Test
    void test1() {
        test(1);
    }

    @Test
    void test2() {
        test(2);
    }

    @Test
    void test3() {
        test(3);
    }

    @Test
    void test4() {
        test(4);
    }

    @Test
    void test5() {
        test(5);
    }

    @Test
    void randomTest() throws IOException {
        final var rand = new EasyRandom();
        final var bound = (int) Math.pow(10, 9);
        final int size = rand.nextInt(200000) + 1;
        final var lst = new ArrayList<Integer>(size);
        for (int i = 0; i < size; i++) {
            lst.add(rand.nextInt(bound) - 100);
        }
        final var expected = naive(lst);
        final var actual = findPair(lst);
        if (!expected.equals(actual)) {
            Files.write(Path.of("/Users/mbelykh/IdeaProjects/YP-Algorithms/src/test/resources/code-200/data"),
                    lst.stream().map(String::valueOf).collect(Collectors.joining(" ")).getBytes(StandardCharsets.UTF_8));
        }
        assertEquals(expected, actual);
    }

    private String naive(List<Integer> ints) {
        int sum = 0;
        for (int i = 0; i < ints.size(); i++) {
            for (int j = i + 1; j < ints.size(); j++) {
                if (i == j) {
                    continue;
                }
                if (Math.abs(ints.get(i) - ints.get(j)) % 200 == 0) {
                    sum++;
                }
            }
        }
        return String.valueOf(sum);
    }

    private String findPair(List<Integer> lst){
        final var map = new HashMap<Integer, Integer>(lst.size());
        for(var num : lst){
            final var left = num % 200;
            map.put(left, map.getOrDefault(left, 0) + 1);
        }
        int sum = 0;

        for (var entry : map.entrySet()) {
            sum += count(entry.getValue());
        }
        return String.valueOf(sum);
    }

    private static long count(int n) {
        if (n < 2) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        return (long) n * (n - 1) / 2;
    }
}