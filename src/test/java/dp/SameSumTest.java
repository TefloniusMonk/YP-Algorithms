package dp;

import base.AbstractInputWrapperTest;
import base.ThrowingFunction;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class SameSumTest extends AbstractInputWrapperTest {

    @Override
    protected ThrowingFunction<InputStream, Object> testingMethod() {
        return SameSum::wrap;
    }

    @Override
    protected String dirname() {
        return "same-sum";
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