import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractInputWrapperTest {


    abstract String dirname();

    protected String getInFilePath() {
        return Paths.get("src", "test", "resources", dirname(), "in").toAbsolutePath().toString();
    }

    protected String getExpectedFilePath() {
        return Paths.get("src", "test", "resources", dirname(), "ex").toAbsolutePath().toString();
    }

    protected void test(int testIdx, ThrowingFunction<InputStream, Object> testMethod) {
        try {
            final var is = new FileInputStream(getInFilePath() + testIdx);
            final var actual = String.valueOf(testMethod.apply(is));
            final var expected = String.join("\n", Files.readAllLines(
                    Path.of(getExpectedFilePath() + testIdx)));
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
