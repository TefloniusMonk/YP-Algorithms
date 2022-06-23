import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CustomMapTaskTest {

    @Test
    void shouldExecuteCommands() throws IOException {
        final var is = new FileInputStream("/Users/mbelykh/IdeaProjects/YP-Algorithms/src/test/resources/in1");
        final var actual = CustomMapTask.inputWrapper(is);
        final var expected = String.join("\n", Files.readAllLines(Path.of("/Users/mbelykh/IdeaProjects/YP-Algorithms/src/test/resources/ex1")));
        assertEquals(expected, actual);
    }
}