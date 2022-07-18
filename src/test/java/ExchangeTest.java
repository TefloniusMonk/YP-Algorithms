import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeTest extends AbstractInputWrapperTest {

    @Override
    ThrowingFunction<InputStream, Object> testingMethod() {
        return Exchange::wrap;
    }

    @Override
    String dirname() {
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