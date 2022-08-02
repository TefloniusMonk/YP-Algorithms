package base;

import graph.RailRoads;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

class RailRoadsTest extends AbstractInputWrapperTest {

    @Override
    protected ThrowingFunction<InputStream, Object> testingMethod() {
        return RailRoads::wrap;
    }

    @Override
    protected String dirname() {
        return "rails";
    }


    @Test
    void test1() {
        test(1);
    }

    @Test
    void test2() {
        test(2);
    }

    @Test
    void test3() {
        test(3);
    }

    @Test
    void test4() {
        test(4);
    }

    @Test
    void test5() {
        test(5);
    }

    @Test
    void test6() {
        test(6);
    }

    @Test
    void test7() {
        test(7);
    }

    @Test
    @Disabled
    void generate() throws Exception {
        final var maxNodes = 5000;
        final StringBuilder res = new StringBuilder(maxNodes + "\n");
        for (int i = 1; i < maxNodes; i++) {
            for (int j = i; j < maxNodes; j++) {
                res.append("B");
            }
            res.append("\n");
        }
        Files.write(Path.of(getInFilePath() + "7"), res.toString().getBytes(StandardCharsets.UTF_8));
    }
}