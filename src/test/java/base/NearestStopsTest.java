package base;

import org.junit.jupiter.api.Test;
import other.NearestStops;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NearestStopsTest {
    private static final String INPUT_NAME = Paths.get("src", "test", "resources", "nearest", "in").toAbsolutePath().toString();
    private static final String EXPECTED_NAME = Paths.get("src", "test", "resources", "nearest", "ex").toAbsolutePath().toString();

    @Test
    void shouldFindNearest1() throws IOException {
        final var testIdx = 1;
        final var is = new FileInputStream(INPUT_NAME + testIdx);
        final var actual = NearestStops.inputWrapper(is);
        final var expected = String.join("\n", Files.readAllLines(
                Path.of(EXPECTED_NAME + testIdx)));
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindNearest2() throws IOException {
        final var testIdx = 2;
        final var is = new FileInputStream(INPUT_NAME + testIdx);
        final var actual = NearestStops.inputWrapper(is);
        final var expected = String.join("\n", Files.readAllLines(
                Path.of(EXPECTED_NAME + testIdx)));
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindNearest3() throws IOException {
        final var testIdx = 3;
        final var is = new FileInputStream(INPUT_NAME + testIdx);
        final var actual = NearestStops.inputWrapper(is);
        final var expected = String.join("\n", Files.readAllLines(
                Path.of(EXPECTED_NAME + testIdx)));
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindLeftBound1() {
        final var lst = List.of(
                new NearestStops.Coordinate("10 0"),
                new NearestStops.Coordinate("20 0"),
                new NearestStops.Coordinate("22 5")
        );
        final var metro = new NearestStops.Coordinate("1 0");
        final var expected = 0;
        assertEquals(expected, NearestStops.maxNearestLeft(lst, metro));
    }

    @Test
    void shouldFindLeftBound2() {
        final var lst = List.of(
                new NearestStops.Coordinate("10 0"),
                new NearestStops.Coordinate("20 0"),
                new NearestStops.Coordinate("52 5")
        );
        final var metro = new NearestStops.Coordinate("52 0");
        final var expected = 2;
        assertEquals(expected, NearestStops.maxNearestLeft(lst, metro));
    }


    @Test
    void shouldFindLeftBound3() {
        final var lst = List.of(
                new NearestStops.Coordinate("10 0"),
                new NearestStops.Coordinate("20 0"),
                new NearestStops.Coordinate("52 5")
        );
        final var metro = new NearestStops.Coordinate("82 0");
        final var expected = 2;
        assertEquals(expected, NearestStops.maxNearestLeft(lst, metro));
    }

    @Test
    void shouldFindLeftBound4() {
        final var lst = List.of(
                new NearestStops.Coordinate("10 0"),
                new NearestStops.Coordinate("20 0"),
                new NearestStops.Coordinate("52 5")
        );
        final var metro = new NearestStops.Coordinate("30 0");
        final var expected = 0;
        assertEquals(expected, NearestStops.maxNearestLeft(lst, metro));
    }


    @Test
    void shouldFindLeftBound5() {
        final var lst = List.of(
                new NearestStops.Coordinate("10 0"),
                new NearestStops.Coordinate("20 0"),
                new NearestStops.Coordinate("52 5")
        );
        final var metro = new NearestStops.Coordinate("-100 0");
        final var expected = 0;
        assertEquals(expected, NearestStops.maxNearestLeft(lst, metro));
    }

    @Test
    void shouldFindLeftBound6() {
        final var lst = List.of(
                new NearestStops.Coordinate("10 0"),
                new NearestStops.Coordinate("20 0"),
                new NearestStops.Coordinate("20 0"),
                new NearestStops.Coordinate("52 5")
        );
        final var metro = new NearestStops.Coordinate("31 0");
        final var expected = 1;
        assertEquals(expected, NearestStops.maxNearestLeft(lst, metro));
    }

    @Test
    void shouldFindRightBound1() {
        final var lst = List.of(
                new NearestStops.Coordinate("10 0"),
                new NearestStops.Coordinate("20 0"),
                new NearestStops.Coordinate("22 5")
        );
        final var metro = new NearestStops.Coordinate("1 0");
        final var expected = 1;
        assertEquals(expected, NearestStops.maxNearestRight(lst, metro));
    }


    @Test
    void shouldFindRightBound2() {
        final var lst = List.of(
                new NearestStops.Coordinate("10 0"),
                new NearestStops.Coordinate("20 0"),
                new NearestStops.Coordinate("21 0"),
                new NearestStops.Coordinate("22 5")
        );
        final var metro = new NearestStops.Coordinate("1 0");
        final var expected = 2;
        assertEquals(expected, NearestStops.maxNearestRight(lst, metro));
    }

    @Test
    void shouldFindRightBound3() {
        final var lst = List.of(
                new NearestStops.Coordinate("10 0"),
                new NearestStops.Coordinate("20 0"),
                new NearestStops.Coordinate("21 0"),
                new NearestStops.Coordinate("21 0"),
                new NearestStops.Coordinate("21 0"),
                new NearestStops.Coordinate("21 0"),
                new NearestStops.Coordinate("21 0"),
                new NearestStops.Coordinate("22 5")
        );
        final var metro = new NearestStops.Coordinate("1 0");
        final var expected = 6;
        assertEquals(expected, NearestStops.maxNearestRight(lst, metro));
    }


    @Test
    void shouldFindRightBound4() {
        final var lst = List.of(
                new NearestStops.Coordinate("22 5"),
                new NearestStops.Coordinate("22 5"),
                new NearestStops.Coordinate("22 5"),
                new NearestStops.Coordinate("22 5"),
                new NearestStops.Coordinate("22 5"),
                new NearestStops.Coordinate("22 5"),
                new NearestStops.Coordinate("22 5"),
                new NearestStops.Coordinate("22 5")
        );
        final var metro = new NearestStops.Coordinate("1 0");
        final var expected = 0;
        assertEquals(expected, NearestStops.maxNearestRight(lst, metro));
    }

    @Test
    void shouldFindRightBound5() {
        final var lst = List.of(
                new NearestStops.Coordinate("10 0"),
                new NearestStops.Coordinate("20 0"),
                new NearestStops.Coordinate("22 5")
        );
        final var metro = new NearestStops.Coordinate("2 5");
        final var expected = 2;
        assertEquals(expected, NearestStops.maxNearestRight(lst, metro));
    }

    @Test
    void shouldFindRightBound6() {
        final var lst = List.of(
                new NearestStops.Coordinate("-44 -34"),
                new NearestStops.Coordinate("-32 -40"),
                new NearestStops.Coordinate("-6 -50"),
                new NearestStops.Coordinate("6 -20"),
                new NearestStops.Coordinate("10 -26"),
                new NearestStops.Coordinate("12 -47"),
                new NearestStops.Coordinate("25 -88"),
                new NearestStops.Coordinate("29 -91"),
                new NearestStops.Coordinate("56 -112"),
                new NearestStops.Coordinate("57 -114"),
                new NearestStops.Coordinate("58 -99"),
                new NearestStops.Coordinate("60 -119"),
                new NearestStops.Coordinate("62 -113"),
                new NearestStops.Coordinate("62 -80"),
                new NearestStops.Coordinate("64 -82")
        );
        final var metro = new NearestStops.Coordinate("66 -99");
        final var expected = 14;
        assertEquals(expected, NearestStops.maxNearestRight(lst, metro));
    }
}