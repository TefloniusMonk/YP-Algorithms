import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PolyHashTest {
    @Test
    void shouldConvert() {
        final var a = 123;
        final var m = 100003;
        final var hash = "hash";
        final var expected = 6080;
        final var actual = PolyHashNaive.polyHash(a, m, hash);
        assertEquals(expected, actual);
    }

    @Test
    void shouldPrint() {
        final var a = 1000;
        final var m = 123987123;
        final var hash = "bhfwkiskdsqjmzgkczvnlnxkfwlwuwdyumitxrfdkpymwckxevebvoeqxuwogymrehoslouqqnslrwpdgsbz";
        final var actual = PolyHashNaive.polyHash(a, m, hash);
        System.out.println(actual);
        //88922821
    }

    @Test
    void shouldComapre() {
        final var a = 1000;
        final var m = 123987123;
        final var hash1 = "cftbvfger";
        final var hash2 = "lhusaekiu";
        final var actual1 = PolyHashNaive.polyHash(a, m, hash1);
        final var actual2 = PolyHashNaive.polyHash(a, m, hash2);
      assertEquals(actual1,actual2);
        //88922821
    }


    @Test
    void shouldConvert2() {
        final var a = 123;
        final var m = 100003;
        final var hash = "a";
        final var expected = 97;
        final var actual = PolyHashNaive.polyHash(a, m, hash);
        assertEquals(expected, actual);
    }

    @Test
    void shouldConvert1() {
        final var a = 123;
        final var m = 100003;
        final var hash = "HaSH";
        final var expected = 56156;
        final var actual = PolyHashNaive.polyHash(a, m, hash);
        assertEquals(expected, actual);
    }

    @Test
    void shouldConvert3() {
        final var a = 434;
        final var m = 133686903;
        final var hash = "iOQmLJwcaRjKeXalPGJfRunGejCpdAVqCnUEQRecwsBNQkC";
        final var expected = 43426443;
        final var actual = PolyHash.polyHash(a, m, hash);
        assertEquals(expected, actual);
    }

    @Test
    void shouldConvert4() {
        final var a = 434;
        final var m = 133686903;
        final var hash = "iOQmLJwcaRjKeXalPGJfRunGejCpdAVqCnUEQRecwsBNQkC";
        final var expected = 43426443;
        final var actual = PolyHashNaive.polyHash(a, m, hash);
        assertEquals(expected, actual);
    }

    @Test
    void shouldWorkSame() {
        final var a = randInt(1, 1001);
        final var m = randInt(1, (int) Math.pow(10, 9) + 1);
        final var hash = randomString(1, (int) Math.pow(10, 4) + 1);

        final var actual = PolyHash.polyHash(a, m, hash);
    }

    @Test
    void shouldFindWithSameHashes() throws Exception {
        final var a = 1000;
        final var m = 123987123;
        final var maxStrLen = 100;
        final var firstStrings = List.of(
                randomString(1, maxStrLen),
                randomString(1, maxStrLen),
                randomString(1, maxStrLen),
                randomString(1, maxStrLen),
                randomString(1, maxStrLen),
                randomString(1, maxStrLen),
                randomString(1, maxStrLen),
                randomString(1, maxStrLen),
                randomString(1, maxStrLen),
                randomString(1, maxStrLen)
        );
        final var firstHashes = firstStrings.stream().map(str -> PolyHash.polyHash(a, m, str)).collect(Collectors.toList());
        final var firstStr = new AtomicReference<String>();
        final var secondStr = new AtomicReference<String>();
        final var stopFlag = new AtomicBoolean(false);
        while (!stopFlag.get()) {
            CompletableFuture.runAsync(() -> {
                final var second = randomString(1, maxStrLen);
                final var secondHash = PolyHash.polyHash(a, m, second);
                for(int i = 0; i< firstStrings.size(); i++) {
                    final var first = firstStrings.get(i);
                    final var firstHash = firstHashes.get(i);
                    final var flag = (secondHash == firstHash && !first.equals(second));
                    if (flag) {
                        stopFlag.set(true);
                        secondStr.set(second);
                        firstStr.set(first);
                    }
                }
            });
        }

        assertTrue(compareHash(firstStr.get(), secondStr.get(), a, m));
        Files.writeString(Path.of(""),
                firstStr.get() + "\n" + secondStr.get());
    }

    private boolean compareHash(String s1, String s2, int a, int m) {
        return PolyHash.polyHash(a, m, s1) == PolyHash.polyHash(a, m, s2);
    }

    private String randomString(int minLength, int maxLength) {
        final var length = randInt(minLength, maxLength);
        final var sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(randChar());
        }
        return sb.toString();
    }

    private char randChar() {
        final var ch = (char) randInt(65, 91);
        return Character.toLowerCase(ch);
    }

    private int randInt(int minVal, int maxVal) {
        return ThreadLocalRandom.current().nextInt(minVal, maxVal);
    }

}