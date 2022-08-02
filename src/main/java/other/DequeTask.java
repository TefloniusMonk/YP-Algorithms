package other;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/*
    Принцип работы.

    Класс other.CircleBufferedDeque хранит массив заданного размера и указатели на head и tail.
    Когда добавляется в начало то индекс head сдвигается влево(Либо переносится в конец массива).
    Когда добавлется в конец индекс сдвигается вправо(Берется остаток от деления по модулю на размер массива).

    Временная сложность.

    O(1) - Все операции выполняются за константное время.

    Пространственная сложность.

    O(N) - Где N максимальная емкость буффера.
 */

// ID = 68999923
public class DequeTask {
    public static void main(String[] args) throws IOException {
        try (final var reader = new BufferedReader(new InputStreamReader(System.in));
             final var writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            //read data
            final var commandsCount = Integer.parseInt(reader.readLine());
            final var capacity = Integer.parseInt(reader.readLine());
            final var commandList = new ArrayList<String>(commandsCount);
            for (int i = 0; i < commandsCount; i++) {
                commandList.add(reader.readLine());
            }

            final var result = executeCommands(capacity, commandList);

            writer.write(result);
        }
    }

    public static String executeCommands(int capacity, List<String> commandList) {
        final var deque = new CircleBufferedDeque(capacity);
        final var sb = new StringBuilder();
        for (var command : commandList) {
            final var res = executeCommand(deque, command);
            if (res != null) {
                sb.append(res)
                        .append("\n");
            }
        }
        return sb.toString();
    }

    public static String executeCommand(CircleBufferedDeque deque, String command) {
        if (command.startsWith("push_front")) {
            return deque.pushFront(command.split(" ")[1]);
        } else if (command.startsWith("push_back")) {
            return deque.pushBack(command.split(" ")[1]);
        } else if (command.startsWith("pop_front")) {
            return deque.popFront();
        } else if (command.startsWith("pop_back")) {
            return deque.popBack();
        } else {
            return "error";
        }
    }
}

class CircleBufferedDeque {
    private static final String ERROR = "error";

    private int head = -1;
    private int tail = -1;
    private final int capacity;
    private final String[] buffer;
    private int size = 0;

    public CircleBufferedDeque(int capacity) {
        this.capacity = capacity;
        this.buffer = new String[capacity];
    }

    public String pushBack(String value) {
        if (size == capacity) {
            return ERROR;
        }
        if (size == 0) {
            head = 0;
        }

        tail = moveRight(tail);
        buffer[tail] = value;
        size++;
        return null;
    }

    public String pushFront(String value) {
        if (size == capacity) {
            return ERROR;
        }
        if (size == 0) {
            tail = 0;
        }

        head = moveLeft(head);
        buffer[head] = value;
        size++;
        return null;
    }

    public String popFront() {
        if (size == 0){ return ERROR;}
        final var tmp = buffer[head];

        head = moveRight(head);
        shrink();
        return tmp;
    }


    public String popBack() {
        if (size == 0){ return ERROR;}
        final var tmp = buffer[tail];

        tail = moveLeft(tail);
        shrink();
        return tmp;
    }

    private void shrink() {
        size--;
        if (size == 0) {
            head = -1;
            tail = -1;
        }
    }

    private int moveLeft(int val) {
        if (val == -1) {
            return 0;
        } else if (val == 0) {
            return capacity - 1;
        } else {
            return val - 1;
        }
    }

    private int moveRight(int val) {
        return (val + 1) % capacity;
    }
}


