package all;

import base.AbstractInputWrapperTest;
import base.ThrowingFunction;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class RomanDigitsTest extends AbstractInputWrapperTest {

    @Override
    protected ThrowingFunction<InputStream, Object> testingMethod() {
        return RomanDigits::wrap;
    }

    @Override
    protected String dirname() {
        return "roman";
    }

    @Test
    void test1() {
        test(1);
    }

    @Test
    void test2() {
        test(2);
    }

    @Test
    void test3() {
        test(3);
    }
}