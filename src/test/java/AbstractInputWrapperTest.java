import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractInputWrapperTest {

    abstract ThrowingFunction<InputStream, Object> testingMethod();

    abstract String dirname();

    protected String getInFilePath() {
        return Paths.get("src", "test", "resources", dirname(), "in").toAbsolutePath().toString();
    }

    protected String getExpectedFilePath() {
        return Paths.get("src", "test", "resources", dirname(), "ex").toAbsolutePath().toString();
    }

    protected void test(int testIdx) {
        try {
            final var is = new FileInputStream(getInFilePath() + testIdx);
            final var actual = stripAllLines(String.valueOf(testingMethod().apply(is)));
            final var expected = stripAllLines(String.join("\n", Files.readAllLines(Path.of(getExpectedFilePath() + testIdx))));
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String stripAllLines(String str) {
        return Arrays.stream(str.split("\n")).map(String::trim).collect(Collectors.joining("\n"));
    }
}
