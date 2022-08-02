package base;

import graph.NearestSights;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

class NearestSightsTest extends AbstractInputWrapperTest{

    @Override
    protected ThrowingFunction<InputStream, Object> testingMethod() {
        return NearestSights::wrap;
    }

    @Override
    protected String dirname() {
        return "sights";
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