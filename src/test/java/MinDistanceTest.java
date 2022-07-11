import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class MinDistanceTest extends AbstractInputWrapperTest {

    @Override
    ThrowingFunction<InputStream, Object> testingMethod() {
        return MinDistance::wrap;
    }

    @Override
    String dirname() {
        return "min-dist";
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


}