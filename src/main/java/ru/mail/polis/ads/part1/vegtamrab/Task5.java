package ru.mail.polis.ads.part1.vegtamrab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task5 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        SimpleQueue queue = new SimpleQueue();
        String command;
        while (!(command = in.next()).isEmpty()) {
            switch (command) {
                case "push":
                    out.println(queue.push(Integer.parseInt(in.next())));
                    break;
                case "size":
                    out.println(queue.size());
                    break;
                case "pop":
                    out.println(queue.pop());
                    break;
                case "front":
                    out.println(queue.front());
                    break;
                case "clear":
                    out.println(queue.clear());
                    break;
                case "exit":
                    out.println("bye");
                    return;
            }
        }
    }

    private static class SimpleQueue {
        private final int QUEUE_MAX_SIZE = 100;
        private int[] array;
        private int begin;
        private int end;
        private int size;

        SimpleQueue() {
            array = new int[QUEUE_MAX_SIZE];
            begin = 0;
            end = 0;
            size = 0;
        }

        String push(int n) {
            if (end == QUEUE_MAX_SIZE) {
                end = 0;
            }
            array[end] = n;
            ++end;
            ++size;
            return "ok";
        }

        int pop() {
            --size;
            if (begin == QUEUE_MAX_SIZE - 1) {
                begin = 0;
                return array[QUEUE_MAX_SIZE - 1];
            }
            return array[++begin];
        }

        int front() {
            return array[begin];
        }

        int size() {
            return size;
        }

        String clear() {
            begin = 0;
            end = 0;
            size = 0;
            return "ok";
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
        } catch (Exception ex) {
            System.out.println();
        }
    }
}
