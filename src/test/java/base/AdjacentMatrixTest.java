package base;

import graph.AdjacentMatrix;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

class AdjacentMatrixTest extends AbstractInputWrapperTest {

    @Override
    protected ThrowingFunction<InputStream, Object> testingMethod() {
        return AdjacentMatrix::wrap;
    }

    @Override
    protected String dirname() {
        return "adjacent-matrix";
    }

    @Test
    void shouldCreateMatrix() {
        test(1);
    }
}