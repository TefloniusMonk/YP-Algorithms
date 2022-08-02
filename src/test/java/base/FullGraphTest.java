package base;

import graph.FullGraph;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

class FullGraphTest extends AbstractInputWrapperTest{

    @Override
    protected ThrowingFunction<InputStream, Object> testingMethod() {
        return FullGraph::wrap;
    }

    @Override
    protected String dirname() {
        return "full-graph";
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