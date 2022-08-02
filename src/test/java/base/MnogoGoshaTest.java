package base;

import hash.MnogoGosha;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MnogoGoshaTest extends AbstractInputWrapperTest {
    @Override
    protected ThrowingFunction<InputStream, Object> testingMethod() {
        return MnogoGosha::inputWrapper;
    }

    @Override
    protected String dirname() {
        return "mnogo";
    }

    @Test
    void shouldFind1() {
        test(1);
    }

    @Test
    void shouldFind2() {
        test(2);
    }

    @Test
    void shouldFind3() {
        test(3);
    }

    @Test
    void shouldFind4() throws Exception {
        final var testIdx = 4;
        final var is = new FileInputStream(getInFilePath() + testIdx);
        final var actual = MnogoGosha.inputWrapper(is);
        final var expected = Arrays.stream(Files.readAllLines(
                Path.of(getExpectedFilePath() + testIdx)).get(0).split(" ")).map(Integer::parseInt).sorted().map(String::valueOf).collect(Collectors.joining(" "));
        assertEquals(expected, actual);
    }


}