package base;

import graph.MST2;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

class MST2Test extends AbstractInputWrapperTest {
    @Override
    protected ThrowingFunction<InputStream, Object> testingMethod() {
        return MST2::wrap;
    }

    @Override
    protected String dirname() {
        return "mst";
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
}