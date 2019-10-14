package ru.mail.polis.ads.part2.kokobox7;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Stack_and_Queue {

    private static class Operation {
        private char operator;
        private Operation right_op;
        private Operation left_op;

        Operation(char operator) {
            this.operator = operator;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            out.println(stackToQueue(in.next()));
        }
    }

    private static String stackToQueue(final String in_str) {
        StringBuilder out_str = new StringBuilder();
        LinkedList<Operation> stack = new LinkedList<Operation>();

        for (char c : in_str.toCharArray()) {
            if (Character.isUpperCase(c)) {
                Operation op = new Operation(c);
                op.right_op = stack.poll();
                op.left_op = stack.poll();
                stack.push(op);
            } else {
                stack.push(new Operation(c));
            }
        }

        while (!stack.isEmpty()) {
            Operation op = stack.pollFirst();
            out_str.insert(0, op.operator);
            if (op.right_op == null) {
                continue;
            } else {
                stack.addLast(op.left_op);
                stack.addLast(op.right_op);
            }
        }

        return out_str.toString();
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
