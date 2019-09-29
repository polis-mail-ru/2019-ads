package ru.mail.polis.ads.part1.blinkyz;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Problem 6125.
 * <p>
 * Link: {@code https://www.e-olymp.com/ru/problems/6125}.
 * Tests: {@code https://www.e-olymp.com/ru/submissions/5736570}.
 */
public class Problem6125 {
    private Problem6125() {

    }

    /**
     * Queue implementation based on ring buffer.
     */
    private static final class ArrayQueue {
        private Integer[] q;

        private final int arraySize = 128;

        private int head;

        private int tail;

        private int size;

        ArrayQueue() {
            q = new Integer[arraySize];
            head = 0;
            tail = 0;
            size = 0;
        }

        Integer push(Integer e) throws IllegalStateException {
            if ((tail + 1 == head) || (head == 0 && tail + 1 == arraySize)) {
                throw new IllegalStateException("Queue is full!");
            }
            q[tail] = e;
            if (tail + 1 == arraySize) {
                tail = 0;
            } else {
                ++tail;
            }
            ++size;
            return e;
        }

        Integer pop() throws NoSuchElementException {
            if (head == tail) {
                throw new NoSuchElementException("Queue is empty");
            }

            Integer e = q[head];
            q[head] = null;
            if (head + 1 == arraySize) {
                head = 0;
            } else {
                ++head;
            }
            --size;
            return e;
        }

        Integer front() throws NoSuchElementException {
            if (head == tail) {
                throw new NoSuchElementException("Queue is empty");
            }
            return q[head];
        }

        int size() {
            return size;
        }

        void clear() {
            while (head != tail) {
                q[head] = null;
                if (head + 1 == arraySize) {
                    head = 0;
                } else {
                    ++head;
                }
            }
            size = 0;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        ArrayQueue q = new ArrayQueue();
        String command;
        while (true) {
            command = in.next();
            switch (command) {
                case "push": {
                    q.push(in.nextInt());
                    System.out.println("ok");
                    break;
                }
                case "pop": {
                    System.out.println(q.pop());
                    break;
                }
                case "front": {
                    System.out.println(q.front());
                    break;
                }
                case "size": {
                    System.out.println(q.size());
                    break;
                }
                case "clear": {
                    q.clear();
                    System.out.println("ok");
                    break;
                }
                case "exit": {
                    System.out.println("bye");
                    return;
                }
                default: {
                    throw new UnsupportedOperationException("Unsupported or unknown queue operation: " + command);
                }
            }
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}