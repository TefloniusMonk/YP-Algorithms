package dp;

import base.AbstractInputWrapperTest;
import base.ThrowingFunction;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

class EndlessATM2Test extends AbstractInputWrapperTest {

    @Override
    protected ThrowingFunction<InputStream, Object> testingMethod() {
        return EndlessATM2::wrap;
    }

    @Override
    protected String dirname() {
        return "atm2";
    }

    @Test
    void test1() {
        test(1);
    }

    @Test
    void    test2() {
        test(2);
    }

    @Test
    void test3(){
        test(3);
    }
}