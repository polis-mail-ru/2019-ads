package ru.mail.polis.ads.part2.Kondrat04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Stack {
    private static void solve(final FastScanner in, final PrintWriter out) {
        ArrayList<Integer> stack = new ArrayList<Integer>();
        String keyWord = in.next();
        while (!keyWord.equals("exit")) {
            switch (keyWord) {
                case "push":
                    stack.add(in.nextInt());
                    System.out.println("ok");
                    break;
                case "pop":
                    if (stack.size() == 0) {
                        System.out.println("error");
                        break;
                    } else {
                        int tail = stack.get(stack.size() - 1);
                        stack.remove(stack.size() - 1);
                        System.out.println(tail);
                        break;
                    }
                case "back":
                    if (stack.size() == 0) {
                        System.out.println("error");
                        break;
                    } else {
                        int tail = stack.get(stack.size() - 1);
                        System.out.println(tail);
                        break;
                    }
                case "size":
                    System.out.println(stack.size());
                    break;
                case "clear":
                    stack.clear();
                    System.out.println("ok");
                    break;
                default:
                    System.out.println("error");
                    break;
            }
            keyWord = in.next();
        }
        out.write("bye");
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
