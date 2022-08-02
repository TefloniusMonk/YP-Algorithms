package base;

import java.util.function.Function;

@FunctionalInterface
public interface ThrowingFunction<T, R> extends Function<T, R> {

    R acceptThrows(T elem) throws Exception;

    @Override
    default R apply(T t){
        try {
            return acceptThrows(t);
        } catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
}