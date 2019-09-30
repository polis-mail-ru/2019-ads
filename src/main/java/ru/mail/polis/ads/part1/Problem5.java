package ru.mail.polis.ads.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * submission - https://www.e-olymp.com/ru/submissions/5738034
 */
public final class Problem5 {

    private static final String EXIT = "exit";
    private static final String OK = "ok";
    private static final String BYE = "bye";
    private static final String PUSH = "push";
    private static final String POP = "pop";
    private static final String FRONT = "front";
    private static final String SIZE = "size";
    private static final String CLEAR = "clear";

    private static class Queue {

        private static final int MAX_CAPACITY = 100;

        int[] elems;
        int head;
        int size;

        private Queue() {
            clear();
        }

        private void push(final int elem) {
            elems[(head + size) % 100] = elem;
            size++;
        }

        private int pop() {
            size--;
            head++;
            return elems[(head - 1) % MAX_CAPACITY];
        }

        private int front() {
            return elems[head % MAX_CAPACITY];
        }

        private int size() {
            return size;
        }

        private void clear() {
            elems = new int[MAX_CAPACITY];
            size = 0;
            head = 0;
        }

    }

    private Problem5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in) {

        final Queue queue = new Queue();

        while (true) {
            final String cmd = in.next();
            if (cmd.equalsIgnoreCase(EXIT)) {
                break;
            }
            switch (cmd) {
                case PUSH: {
                    queue.push(Integer.parseInt(in.next()));
                    System.out.println(OK);
                    break;
                }
                case POP: {
                    System.out.println(queue.pop());
                    break;
                }
                case FRONT: {
                    System.out.println(queue.front());
                    break;
                }
                case SIZE: {
                    System.out.println(queue.size());
                    break;
                }
                case CLEAR: {
                    queue.clear();
                    System.out.println(OK);
                    break;
                }
                default: {
                    break;
                }
            }
        }
        System.out.println(BYE);
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
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
        solve(in);
    }
}
