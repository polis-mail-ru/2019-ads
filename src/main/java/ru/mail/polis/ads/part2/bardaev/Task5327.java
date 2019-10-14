package ru.mail.polis.ads.part2.bardaev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task5327 {
    private Task5327() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {

        String str = in.next();
        ArrayDeque<Boolean> arr = new ArrayDeque<>();

        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') count++;
            if (str.charAt(i) == ')') count--;
        }

        if (count == 0) {
            out.println("YES");
        } else {
            out.println("NO");
        }
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
