package dp;

import base.AbstractInputWrapperTest;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class AdultFibTest {

    @Test
    void test1() throws Exception{
        assertEquals("1",
                AdultFib.wrap(new ByteArrayInputStream("1".getBytes(StandardCharsets.UTF_8))));
    }

    @Test
    void test2() throws Exception{
        assertEquals("2",
                AdultFib.wrap(new ByteArrayInputStream("2".getBytes(StandardCharsets.UTF_8))));
    }

    @Test
    void test3() throws Exception{
        assertEquals("89",
                AdultFib.wrap(new ByteArrayInputStream("10".getBytes(StandardCharsets.UTF_8))));
    }
}