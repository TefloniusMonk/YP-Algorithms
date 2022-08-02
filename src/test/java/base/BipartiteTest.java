package base;

import graph.Bipartite;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

class BipartiteTest extends AbstractInputWrapperTest{

    @Override
    protected ThrowingFunction<InputStream, Object> testingMethod() {
        return Bipartite::wrap;
    }

    @Override
    protected String dirname() {
        return "bipartite";
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