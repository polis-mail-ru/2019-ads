package ru.mail.polis.ads.part2.DiscreetDmitriy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Task5InfiniteStack {

    private static LinkedList<Integer> stack = new LinkedList<>();

    private static void solve(final FastScanner in) {
        whileLabel:
        while (true) {
            String line = in.next();
            switch (line) {
                case "push":
                    push(in);
                    break;
                case "pop":
                    pop();
                    break;
                case "back":
                    back();
                    break;
                case "size":
                    size();
                    break;
                case "clear":
                    clear();
                    break;
                default:
                    System.out.println("bye");
                    break whileLabel;
            }
        }
    }

    private static void push(final FastScanner in) {
        final int n = in.nextInt();
        stack.push(n);
        System.out.println("ok");
    }

    private static void pop() {
        if (stack.isEmpty())
            System.out.println("error");
        else
            System.out.println(stack.pop());
    }

    private static void back() {
        if (stack.isEmpty())
            System.out.println("error");
        else
            System.out.println(stack.peek());
    }

    private static void size() {
        System.out.println(stack.size());
    }

    private static void clear() {
        stack.clear();
        System.out.println("ok");
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
        solve(in);
    }
}

