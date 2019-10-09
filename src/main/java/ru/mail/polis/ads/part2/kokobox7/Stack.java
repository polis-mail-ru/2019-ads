package ru.mail.polis.ads.part2.kokobox7;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;


public class Stack {

    private static class MyStack {
        private ArrayDeque<Integer> array;
        private PrintWriter out;

        MyStack(PrintWriter out) {
            this.array = new ArrayDeque<>();
            this.out = out;
        }

        public void push(int n) {
            array.addLast(n);
            out.println("ok");
        }

        public void pop() {
            if (array.isEmpty()) {
                out.println("error");
            } else {
                int elem = array.pollLast();
                out.println(elem);
            }
        }

        public void back() {
            if (array.isEmpty()) {
                out.println("error");
            } else {
                out.println(array.peekLast());
            }
        }

        public void size() {
            out.println(array.size());
        }

        public void clear() {
            array.clear();
            out.println("ok");
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final MyStack stack = new MyStack(out);
        String input = "";
        while (!"exit".equals(input)) {
            input = in.next();

            if ("push".equals(input)) {
                stack.push(in.nextInt());
            } else if ("pop".equals(input)) {
                stack.pop();
            } else if ("back".equals(input)) {
                stack.back();
            } else if ("size".equals(input)) {
                stack.size();
            } else if ("clear".equals(input)) {
                stack.clear();
            }
        }
        out.println("bye");
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