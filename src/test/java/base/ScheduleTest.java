package base;

import dp.Schedule;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

class ScheduleTest extends AbstractInputWrapperTest {

    @Override
    protected ThrowingFunction<InputStream, Object> testingMethod() {
        return Schedule::wrap;
    }

    @Override
    protected String dirname() {
        return "sch";
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