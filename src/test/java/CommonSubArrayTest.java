import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class CommonSubArrayTest extends AbstractInputWrapperTest{

    @Override
    ThrowingFunction<InputStream, Object> testingMethod() {
        return CommonSubArray::inputWrapper;
    }

    @Override
    String dirname() {
        return "subarray";
    }

    @Test
    void shouldFindMaxSubArraySize1(){
        test(1);
    }

    @Test
    void shouldFindMaxSubArraySize2(){
        test(2);
    }

    @Test
    void shouldFindMaxSubArraySize3(){
        test(3);
    }

    @Test
    void shouldFindMaxSubArraySize4(){
        test(4);
    }
}