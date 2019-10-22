package ru.mail.polis.ads.part2.Kopeyka885;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task6124{

    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack<Integer> stack = new Stack<>();
        String operation = in.next();
        while (!operation.contains("exit")) {
            switch (operation) {
                case "push":
                    stack.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    try {
                        out.println(stack.pop());
                    } catch (Exception e) {
                        out.println("error");
                    }
                    break;
                case "back":
                    try {
                        out.println(stack.peek());
                    } catch (Exception e) {
                        out.println("error");
                    }
                    break;
                case "size":
                    out.println(stack.size());
                    break;
                case "clear":
                    stack.clear();
                    out.println("ok");
                    break;
            }
            operation = in.next();
        }
        out.println("bye");
        out.flush();
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