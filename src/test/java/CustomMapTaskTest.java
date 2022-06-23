import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CustomMapTaskTest {

    @Test
    @Disabled
    void shouldExecuteCommands() throws IOException {
        final var is = new FileInputStream("");
        final var actual = CustomMapTask.inputWrapper(is);
        final var expected = String.join("\n", Files.readAllLines(Path.of("")));
        assertEquals(expected, actual);
    }
}