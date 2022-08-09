package str;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Character.isAlphabetic;
import static java.lang.Character.isDigit;


/*
    Принцип работы.

        Сначала распаковываем все строки, потом проходим по i символу всех строк пока не найдем несовпадающий
    или не дойдем до минимальной длины строк.
        Распаковка строк работает так: Идем по строке, если встречаем число, то доходим до первого нечислового символа
    и парсим все число целиком - это множитель выражения, которое будет дальше. Потом идем дальше по строке,
    если нашли символ '[', значит это начало выражения, идем по строке до конца выражения и создаем объект Expression.
    Если мы встретили буквенный символ, то идем по строке до тех пор, пока не встретим небуквенный символ,
    из полученной подстроки так же создаем Expression.
        Потом для каждого полученного объекта Expression рекурсивно повторяем процедуру распаковки.
     Если Expression простой(флаг isSimple, он будет равен true, если это буквенное выражение(Например abc)
     или при парсинге выражения в квадратных скобках мы не встретили '[', кроме начального)
        Когда все вложенные выражения распаковали, повторяем строку столько раз, сколько указано в Expression.multiplier
     Все вычисления функции unpack кэшируем(Очевидно, чтобы не вычислять второй раз)

    Временная сложность.

    O( N*M + N*(I^2 + L) ) - N * M поиск общего префикса, где N число строк, а M минимальная длина строки.
        Распаковка запакованной строки работает за:
        O(N * (I^2 + L)),
        N - кол-во строк
        I - длина запакованной строки, I^2, т.к. в худшем случае у нас будет строка вида X1[X2[X3...]]]
            и мы сначала выделим выражение X1 * X2[...], потом X2 * [...] и т.д. При этом мы идем по выражению до тех пор,
            пока не найдем нужную закрывающую скобку(Которая сначала будет последним символом, потом предпоследним и т.д.)
        L - длина распакованной строки.

     UPD: Может быть кейс 1 * [ 1 * [ ... ] ](Встречал такое в тестах), так что в худшем случае будет больше чем logL.
     Если же исходить из логичных соображений, что множитель должен быть больше 1, то мы получим что-то вроде L * logL
     для распаковки.
     Так что сложность будет O( N * (M + L * log(L)) в среднем, в лучшем случае O(N * (M + L)) (Если все строки будут вида "abcd"),
     а в худшем если будет строка 1[1[1[1[1[... ]]]], и внутри будет длинная строка максимальной длины(По условию 10^5),
     то распаковка тут будет работать за L*K где K глубина рекурсии(В принципе, может быть любой)

    Пространственная сложность.

    O( L + I ) — L - суммарная длина строк, I - суммарное число выражений
 */

// ID = 69595363
public class PackedPrefix {
    private static final Map<String, String> cache = new HashMap<>();

    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    private static List<String> readList(BufferedReader reader, int size) throws IOException {
        final List<String> lst = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            lst.add(reader.readLine());
        }
        return lst;
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final int strCount = Integer.parseInt(reader.readLine());
            final List<String> strs = readList(reader, strCount);
            final List<String> unpacked = strs.stream()
                    .map(PackedPrefix::unpack)
                    .collect(Collectors.toList());
            return findCommonPrefix(unpacked);
        }
    }

    private static String findCommonPrefix(List<String> strings) {
        final int minSize = strings.stream().mapToInt(String::length).min().getAsInt();
        final StringBuilder builder = new StringBuilder();
        final String sample = strings.get(0);
        for (int i = 0; i < minSize; i++) {
            final char ch = sample.charAt(i);
            for (String str : strings) {
                if (str.charAt(i) != ch) {
                    return builder.toString();
                }
            }
            builder.append(ch);
        }
        return builder.toString();
    }

    private static String unpack(String s) {
        if (cache.containsKey(s)) {
            return cache.get(s);
        }
        final List<Expression> expressions = split(s);
        if (expressions.size() == 0) {
            return "";
        }
        final String result;
        if (expressions.size() == 1 && expressions.get(0).isSimple) {
            final Expression expr = expressions.get(0);
            result = expr.expression.repeat(expr.multiplier);
        } else {
            result = expressions.stream()
                    .map(expression -> {
                        if (expression.isSimple) {
                            return expression.expression.repeat(expression.multiplier);
                        } else {
                            return unpack(expression.expression).repeat(expression.multiplier);
                        }
                    })
                    .collect(Collectors.joining(""));
        }
        cache.put(s, result);
        return result;
    }

    private static List<Expression> split(String s) {
        final List<Expression> collector = new LinkedList<>();
        final var counter = new Counter();
        var expr = new Expression();
        while (counter.end < s.length()) {
            if (expr.expression != null) {
                collector.add(expr);
                expr = new Expression();
            }
            if (isDigit(s.charAt(counter.start))) {
                parseMultiplier(s, counter, expr);
            }
            if (isAlphabetic(s.charAt(counter.start))) {
                parseSimpleExpression(s, counter, expr);
            } else {
                parseComplexExpression(s, counter, expr);
            }
        }
        if (expr.expression != null) {
            collector.add(expr);
        }
        return collector;
    }

    private static void parseComplexExpression(String s, Counter counter, Expression expr) {
        expr.isSimple = true;
        final var builder = new StringBuilder();
        int balance = 1;
        while (balance != 0) {
            counter.end++;
            final var curCh = s.charAt(counter.end);
            if (curCh == ']') {
                balance--;
                if (balance == 0) {
                    break;
                }
            }
            builder.append(curCh);
            if (curCh == '[') {
                expr.isSimple = false;
                balance++;
            }
        }
        expr.expression = builder.toString();
        counter.start = ++counter.end;
    }

    private static void parseSimpleExpression(String s, Counter counter, Expression expr) {
        final var builder = new StringBuilder();
        while (counter.end < s.length() && isAlphabetic(s.charAt(counter.end))) {
            builder.append(s.charAt(counter.end));
            counter.end++;
        }
        expr.expression = builder.toString();
        counter.start = counter.end;
        expr.isSimple = true;
    }

    private static void parseMultiplier(String s, Counter counter, Expression expr) {
        while (isDigit(s.charAt(counter.end))) {
            counter.end++;
        }
        expr.multiplier = Integer.parseInt(s.substring(counter.start, counter.end));
        counter.start = counter.end;
    }

    private static class Counter {
        public int start;
        public int end;

        private Counter() {
            start = 0;
            end = 0;
        }
    }

    private static class Expression {
        public boolean isSimple;
        public String expression;
        public int multiplier;
    }
}
