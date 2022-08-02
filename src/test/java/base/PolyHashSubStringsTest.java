package base;

import hash.PolyHashSubStrings;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class PolyHashSubStringsTest {
    private static final String INPUT_NAME = Paths.get("src","test","resources", "subhashes", "in").toAbsolutePath().toString();
    private static final String EXPECTED_NAME = Paths.get("src","test","resources", "subhashes", "ex").toAbsolutePath().toString();

    @Test
    void shouldComputeHashes1() throws IOException {
        final var testIdx = 1;
        final var is = new FileInputStream(INPUT_NAME + testIdx);
        final var actual = PolyHashSubStrings.inputWrapper(is);
        final var expected = String.join("\n", Files.readAllLines(
                Path.of(EXPECTED_NAME + testIdx)));
        assertEquals(expected, actual);
    }
}