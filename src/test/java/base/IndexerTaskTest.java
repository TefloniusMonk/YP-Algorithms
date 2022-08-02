package base;

import hash.IndexerTask;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class IndexerTaskTest {
    private static final String INPUT_NAME = Paths.get("src","test","resources", "index", "in").toAbsolutePath().toString();
    private static final String EXPECTED_NAME = Paths.get("src","test","resources", "index", "ex").toAbsolutePath().toString();


    @Test
    void shouldReturnMostRelevant1() throws IOException {
        final var testIdx = 1;
        final var is = new FileInputStream(INPUT_NAME + testIdx);
        final var actual = IndexerTask.inputWrapper(is);
        final var expected = String.join("\n", Files.readAllLines(
                Path.of(EXPECTED_NAME + testIdx)));
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnMostRelevant2() throws IOException {
        final var testIdx = 2;
        final var is = new FileInputStream(INPUT_NAME + testIdx);
        final var actual = IndexerTask.inputWrapper(is);
        final var expected = String.join("\n", Files.readAllLines(
                Path.of(EXPECTED_NAME + testIdx)));
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnMostRelevant3() throws IOException {
        final var testIdx = 3;
        final var is = new FileInputStream(INPUT_NAME + testIdx);
        final var actual = IndexerTask.inputWrapper(is);
        final var expected = String.join("\n", Files.readAllLines(
                Path.of(EXPECTED_NAME + testIdx)));
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnMostRelevant4() throws IOException {
        final var testIdx = 4;
        final var is = new FileInputStream(INPUT_NAME + testIdx);
        final var actual = IndexerTask.inputWrapper(is);
        final var expected = String.join("\n", Files.readAllLines(
                Path.of(EXPECTED_NAME + testIdx)));
        assertEquals(expected, actual);
    }
}