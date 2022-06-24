import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MnogoGoshaTest {
    private static final String INPUT_NAME = Paths.get("src","test","resources", "mnogo", "in").toAbsolutePath().toString();
    private static final String EXPECTED_NAME = Paths.get("src","test","resources", "mnogo", "ex").toAbsolutePath().toString();

    @Test
    void shouldFind1() throws Exception{
        final var testIdx = 1;
        final var is = new FileInputStream(INPUT_NAME + testIdx);
        final var actual = MnogoGosha.inputWrapper(is);
        final var expected = String.join("\n", Files.readAllLines(
                Path.of(EXPECTED_NAME + testIdx)));
        assertEquals(expected, actual);
    }

    @Test
    void shouldFind2() throws Exception{
        final var testIdx = 2;
        final var is = new FileInputStream(INPUT_NAME + testIdx);
        final var actual = MnogoGosha.inputWrapper(is);
        final var expected = String.join("\n", Files.readAllLines(
                Path.of(EXPECTED_NAME + testIdx)));
        assertEquals(expected, actual);
    }

    @Test
    void shouldFind3() throws Exception{
        final var testIdx = 3;
        final var is = new FileInputStream(INPUT_NAME + testIdx);
        final var actual = MnogoGosha.inputWrapper(is);
        final var expected = String.join("\n", Files.readAllLines(
                Path.of(EXPECTED_NAME + testIdx)));
        assertEquals(expected, actual);
    }

    @Test
    void shouldFind4() throws Exception{
        final var testIdx = 4;
        final var is = new FileInputStream(INPUT_NAME + testIdx);
        final var actual = MnogoGosha.inputWrapper(is);
        final var expected = Arrays.stream(Files.readAllLines(
                Path.of(EXPECTED_NAME + testIdx)).get(0).split(" ")).map(Integer::parseInt).sorted().map(String::valueOf).collect(Collectors.joining(" "));
        assertEquals(expected, actual);
    }
}