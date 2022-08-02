package base;

import dp.Exchange;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

class ExchangeTest extends AbstractInputWrapperTest {

    @Override
    protected ThrowingFunction<InputStream, Object> testingMethod() {
        return Exchange::wrap;
    }

    @Override
    protected String dirname() {
        return "exchange";
    }

    @Test
    void test1(){
        test(1);
    }

    @Test
    void test2(){
        test(2);
    }
}