import org.junit.jupiter.api.Test;

import java.io.InputStream;

class ConnectivityTest extends AbstractInputWrapperTest {

    @Override
    ThrowingFunction<InputStream, Object> testingMethod() {
        return Connectivity::wrap;
    }

    @Override
    String dirname() {
        return "connectivity";
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