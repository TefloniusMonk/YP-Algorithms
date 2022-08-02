package base;

import graph.MST;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

class MSTTest extends AbstractInputWrapperTest {

    @Override
    protected ThrowingFunction<InputStream, Object> testingMethod() {
        return MST::wrap;
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