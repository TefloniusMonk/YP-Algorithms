package all;

import all.CardCounter;
import base.AbstractInputWrapperTest;
import base.ThrowingFunction;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

class CardCounterTest extends AbstractInputWrapperTest {

    @Override
    protected ThrowingFunction<InputStream, Object> testingMethod() {
        return CardCounter::wrap;
    }

    @Override
    protected String dirname() {
        return "card-counter";
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

    @Test
    void test5() {
        test(5);
    }

    @Test
    void test6() {
        test(6);
    }

    @Test
    void test7() {
        test(7);
    }
}