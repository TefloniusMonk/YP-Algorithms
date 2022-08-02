package base;

import graph.DFS;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

class DFSTest extends AbstractInputWrapperTest {

    @Override
    protected ThrowingFunction<InputStream, Object> testingMethod() {
        return DFS::wrap;
    }

    @Override
    protected String dirname() {
        return "dfs";
    }

    @Test
    void shouldWalk() {
        test(1);
    }

    @Test
    void shouldWalk2() {
        test(2);
    }
}
