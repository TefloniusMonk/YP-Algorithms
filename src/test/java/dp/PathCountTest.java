package dp;

import base.AbstractInputWrapperTest;
import base.ThrowingFunction;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class PathCountTest extends AbstractInputWrapperTest {

    @Override
    protected ThrowingFunction<InputStream, Object> testingMethod() {
        return PathCount::wrap;
    }

    @Override
    protected String dirname() {
        return "path-count";
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

    @Test
    void test4() {
        test(4);
    }
}