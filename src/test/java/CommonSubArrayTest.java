import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonSubArrayTest extends AbstractInputWrapperTest{

    @Override
    String dirname() {
        return "subarray";
    }

    @Test
    void shouldFindMaxSubArraySize1(){
        test(1, CommonSubArray::inputWrapper);
    }

    @Test
    void shouldFindMaxSubArraySize2(){
        test(2, CommonSubArray::inputWrapper);
    }

    @Test
    void shouldFindMaxSubArraySize3(){
        test(3, CommonSubArray::inputWrapper);
    }

    @Test
    void shouldFindMaxSubArraySize4(){
        test(4, CommonSubArray::inputWrapper);
    }
}