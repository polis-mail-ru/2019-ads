package main.java.ru.mail.polis.ads.part2.Eretic431;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * https://www.e-olymp.com/ru/submissions/5858962
 */

public class Task5 {
    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);

        final Deque<Integer> stack = new ArrayDeque<>();
        String input = "";

        while (!"exit".equals(input)) {
            input = in.next();

            if ("push".equals(input)) {
                stack.addLast(in.nextInt());
                System.out.println("ok");
            } else if ("pop".equals(input)) {
                System.out.println(stack.isEmpty() ? "error" : stack.pollLast());
            } else if ("back".equals(input)) {
                System.out.println(stack.isEmpty()  ? "error" : stack.peekLast());
            } else if ("size".equals(input)) {
                System.out.println(stack.size());
            } else if ("clear".equals(input)) {
                stack.clear();
                System.out.println("ok");
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
}
