import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyListTest extends AbstractInputWrapperTest {

    @Override
    ThrowingFunction<InputStream, Object> testingMethod() {
        return AdjacencyList::wrap;
    }

    @Override
    String dirname() {
        return "adjacent";
    }

    @Test
    void shouldCreateList(){
        test(1);
    }
}