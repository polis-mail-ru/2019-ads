package ru.mail.polis.ads.part1.medalexey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 *  Название задачи: "Простая очередь"
 *  Тестирование: https://www.e-olymp.com/ru/submissions/5728980
 *
 */
public class SimpleQueue {

    private SimpleQueue() {

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Deque<Integer> queue = new ArrayDeque<>();
        String input;

        while (true) {
            input = in.next();

            if (input.matches("push")) {
                queue.addLast(in.nextInt());
                out.write("ok\n");
            } else if (input.matches("pop")) {
                out.write(queue.pollFirst() + "\n");
            } else if (input.matches("front")) {
                out.write(queue.peekFirst() + "\n");
            } else if (input.matches("size")) {
                out.write(queue.size() + "\n");
            } else if (input.matches("clear")) {
                queue = new ArrayDeque<>();
                out.write("ok\n");
            } else if (input.matches("exit")) {
                out.write("bye");
                out.flush();
                System.exit(0);
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
