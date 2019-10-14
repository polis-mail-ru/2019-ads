package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class SolveTemplate {
    private SolveTemplate() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        List<Integer> list = new ArrayList<>();
        String command = in.next();
        while (!command.contains("exit")) {
            switch (command) {
                case ("push"):
                    Integer number = Integer.parseInt(in.next());
                    list.add(number);
                    System.out.println("ok");
                    break;
                case ("pop"):
                    System.out.println(list.get(0));
                    list.remove(0);
                    break;
                case ("front"):
                    System.out.println(list.get(0));
                    break;
                case ("size"):
                    System.out.println(list.size());
                    break;
                case ("clear"):
                    list.clear();
                    System.out.println("ok");
                    break;
            }
            command = in.next();
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
