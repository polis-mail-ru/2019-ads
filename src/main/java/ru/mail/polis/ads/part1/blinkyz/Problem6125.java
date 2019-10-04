package ru.mail.polis.ads.part1.blinkyz;

import ru.mail.polis.ads.FastScanner;

import java.io.PrintWriter;
import java.util.NoSuchElementException;

public class Problem6125 {
    private Problem6125() {

    }

    /**
     * Queue implementation based on ring buffer.
     */
    private static final class ArrayQueue {
        private Integer[] queue;

        private static final int ARRAY_SIZE = 128;

        private int head;

        private int tail;

        private int size;

        ArrayQueue() {
            queue = new Integer[ARRAY_SIZE];
            head = 0;
            tail = 0;
            size = 0;
        }

        void push(final Integer elem) throws IllegalStateException {
            if (tail + 1 == head || head == 0 && tail + 1 == ARRAY_SIZE) {
                throw new IllegalStateException("Queue is full!");
            }
            queue[tail] = elem;
            if (tail + 1 == ARRAY_SIZE) {
                tail = 0;
            } else {
                ++tail;
            }
            ++size;
        }

        Integer pop() throws NoSuchElementException {
            if (head == tail) {
                throw new NoSuchElementException("Queue is empty");
            }

            final Integer elem = queue[head];
            queue[head] = null;
            if (head + 1 == ARRAY_SIZE) {
                head = 0;
            } else {
                ++head;
            }
            --size;
            return elem;
        }

        Integer front() throws NoSuchElementException {
            if (head == tail) {
                throw new NoSuchElementException("Queue is empty");
            }
            return queue[head];
        }

        int size() {
            return size;
        }

        void clear() {
            while (head != tail) {
                queue[head] = null;
                if (head + 1 == ARRAY_SIZE) {
                    head = 0;
                } else {
                    ++head;
                }
            }
            size = 0;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final ArrayQueue q = new ArrayQueue();
        String command;
        while (true) {
            command = in.next();
            switch (command) {
                case "push": {
                    q.push(in.nextInt());
                    out.println("ok");
                    break;
                }
                case "pop": {
                    out.println(q.pop());
                    break;
                }
                case "front": {
                    out.println(q.front());
                    break;
                }
                case "size": {
                    out.println(q.size());
                    break;
                }
                case "clear": {
                    q.clear();
                    out.println("ok");
                    break;
                }
                case "exit": {
                    out.println("bye");
                    return;
                }
                default: {
                    throw new UnsupportedOperationException("Unsupported or unknown queue operation: " + command);
                }
            }
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}