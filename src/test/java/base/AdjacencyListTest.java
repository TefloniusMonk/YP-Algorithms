package base;

import graph.AdjacencyList;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

class AdjacencyListTest extends AbstractInputWrapperTest {

    @Override
    protected ThrowingFunction<InputStream, Object> testingMethod() {
        return AdjacencyList::wrap;
    }

    @Override
    protected String dirname() {
        return "adjacent";
    }

    @Test
    void shouldCreateList(){
        test(1);
    }
}