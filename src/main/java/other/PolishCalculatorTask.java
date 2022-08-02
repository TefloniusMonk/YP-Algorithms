package other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/*
    Принцип работы.

    Считываем всю строку, делим на элементы и для каждого элемента выполняем соответствующую операцию
    (Либо добавляем в стек либо выполняем операцию над двумя предыдущими числами).

    Временная сложность.

    O(N) - Где N кол-во элементов в входной строке(Операнды и операторы).
    Время растет линейно, т.к. для каждого элемента выполняется константное число операций

    Пространственная сложность.

    O(N) - Где N кол-во элементов в входной строке(Операнды и операторы). Максимальный размер стека будет (2/3 * N), т.е. зависимость линейная.
 */

// ID = 68982379
public class PolishCalculatorTask {
    private static final String OPERATORS = "+-*/";
    private static final Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        try (final var reader = new BufferedReader(new InputStreamReader(System.in))) {
            final var expression = readList(reader);
            final var result = compute(expression);
            System.out.println(result);
        }
    }

    public static int compute(List<String> elements) {
        for (var el : elements) {
            if (isOperand(el)) {
                stack.push(Integer.parseInt(el));
            } else {
                final var result = executeOperation(stack.pop(), stack.pop(), el);
                stack.push(result);
            }
        }
        return stack.pop();
    }

    private static Integer executeOperation(Integer second, Integer first, String operator) {
        switch (operator) {
            case "+":
                return first + second;
            case "-":
                return first - second;
            case "*":
                return first * second;
            case "/":
                return (int) Math.floor((double) first / second);
            default:
                return 1;
        }
    }

    private static boolean isOperation(String el) {
        return OPERATORS.contains(el);
    }

    private static boolean isOperand(String el) {
        return !isOperation(el);
    }


    private static List<String> readList(BufferedReader reader) throws IOException {
        final var lst = new ArrayList<String>();
        final var tokenizer = new StringTokenizer(reader.readLine());
        while (tokenizer.hasMoreTokens()) {
            lst.add(tokenizer.nextToken());
        }
        return lst;
    }
}

