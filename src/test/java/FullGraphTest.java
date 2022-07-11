import org.junit.jupiter.api.Test;

import java.io.InputStream;

class FullGraphTest extends AbstractInputWrapperTest{

    @Override
    ThrowingFunction<InputStream, Object> testingMethod() {
        return FullGraph::wrap;
    }

    @Override
    String dirname() {
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
}