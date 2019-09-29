package ru.mail.polis.ads.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * submission - https://www.e-olymp.com/ru/submissions/5738034
 */
public final class Problem5 {

    private static class Queue {

        private static final int MAX_CAPACITY = 100;

        int[] elems;
        int head;
        int size;

        private Queue() {
            clear();
        }

        private void push(int elem) {
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

    private static void solve(final FastScanner in, final PrintWriter out) {

        Queue queue = new Queue();

        while (true) {
            String cmd = in.next();
            if (cmd.equalsIgnoreCase("exit")) {
                break;
            }
            switch (cmd) {
                case "push": {
                    queue.push(Integer.parseInt(in.next()));
                    System.out.println("ok");
                    break;
                }
                case "pop": {
                    System.out.println(queue.pop());
                    break;
                }
                case "front": {
                    System.out.println(queue.front());
                    break;
                }
                case "size": {
                    System.out.println(queue.size());
                    break;
                }
                case "clear": {
                    queue.clear();
                    System.out.println("ok");
                    break;
                }
                default: {

                }
            }
        }
        System.out.println("bye");
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
