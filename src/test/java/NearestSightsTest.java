import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class NearestSightsTest extends AbstractInputWrapperTest{

    @Override
    ThrowingFunction<InputStream, Object> testingMethod() {
        return NearestSights::wrap;
    }

    @Override
    String dirname() {
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