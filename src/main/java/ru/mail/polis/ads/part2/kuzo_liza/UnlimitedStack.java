package ru.mail.polis.ads.part2.kuzo_liza;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class UnlimitedStack {

    private UnlimitedStack() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String str;
        Deque<Integer> stack = new ArrayDeque();

        while (true) {
            str = in.next();
            if (str.equals("push")) {
                stack.addLast(in.nextInt());
                System.out.println("ok");
            } else if (str.equals("pop")) {
                System.out.println(stack.isEmpty() ? "error" : stack.pollLast());
            } else if (str.equals("back")) {
                System.out.println(stack.isEmpty() ? "error" : stack.getLast());
            } else if (str.equals("size")) {
                System.out.println(stack.size());
            } else if (str.equals("clear")) {
                stack = new ArrayDeque<>();
                System.out.println("ok");
            } else if (str.equals("exit")) {
                System.out.println("bye");
                System.exit(0);
            }
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
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
}