package base;

import graph.BFS;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

class BFSTest extends AbstractInputWrapperTest {

    @Override
    protected ThrowingFunction<InputStream, Object> testingMethod() {
        return BFS::wrap;
    }

    @Override
    protected String dirname() {
        return "bfs";
    }

    @Test
    void shouldWalk1() {
        test(1);
    }

    @Test
    void shouldWalk2() {
        test(2);
    }
}