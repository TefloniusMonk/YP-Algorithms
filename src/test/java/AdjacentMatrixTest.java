import org.junit.jupiter.api.Test;

import java.io.InputStream;

class AdjacentMatrixTest extends AbstractInputWrapperTest {

    @Override
    ThrowingFunction<InputStream, Object> testingMethod() {
        return AdjacentMatrix::wrap;
    }

    @Override
    String dirname() {
        return "adjacent-matrix";
    }

    @Test
    void shouldCreateMatrix() {
        test(1);
    }
}