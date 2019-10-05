package ru.mail.polis.ads.part2.medalexey;

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;


/**
 *  Задача: Стек неограниченного размера (https://www.e-olymp.com/ru/problems/6124)
 *  Тестирование: https://www.e-olymp.com/ru/submissions/5782965
 */
public class Stack {

    private Stack() {

    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        final Deque<Integer> stack = new ArrayDeque<>();
        String input = "";

        while (!"exit".equals(input)) {
            input = in.next();

            if ("push".equals(input)) {
                stack.addLast(in.nextInt());
                out.println("ok");
            } else if ("pop".equals(input)) {
                out.println(
                        stack.isEmpty() ? "error" : stack.pollLast()
                );
            } else if ("back".equals(input)) {
                out.println(
                        stack.isEmpty()  ? "error" : stack.peekLast()
                );
            } else if ("size".equals(input)) {
                out.println(stack.size());
            } else if ("clear".equals(input)) {
                stack.clear();
                out.println("ok");
            }

        }

        out.print("bye");
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
