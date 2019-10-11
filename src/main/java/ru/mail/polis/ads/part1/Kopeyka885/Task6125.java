package ru.mail.polis.ads.part1.Kopeyka885;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public final class Task6125{
    private static void solve(final FastScanner in, final PrintWriter out) {
        ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
        String instruction = in.next();
        while (!instruction.contains("exit")) {
            switch (instruction) {
                case ("push"):
                    Integer number = Integer.parseInt(in.next());
                    queue.addLast(number);
                    System.out.println("ok");
                    break;
                case ("pop"):
                    System.out.println(queue.element());
                    queue.pop();
                    break;
                case ("front"):
                    System.out.println(queue.element());
                    break;
                case ("size"):
                    System.out.println(queue.size());
                    break;
                case ("clear"):
                    queue.clear();
                    System.out.println("ok");
                    break;
            }
            instruction = in.next();
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}