import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class DFSTest extends AbstractInputWrapperTest {

    @Override
    ThrowingFunction<InputStream, Object> testingMethod() {
        return DFS::wrap;
    }

    @Override
    String dirname() {
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
