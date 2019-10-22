package ru.mail.polis.ads.part5.gogun;

import java.io.*;
import java.util.StringTokenizer;

public class Task2 {

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final BufferedInputStream in) {
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }

    private static void solve(FastScanner input, PrintWriter output) {
        long w = input.nextLong();
        long h = input.nextLong();
        long n = input.nextLong();

        long left = Math.max(w, h);
        long right = n * left;
        while (left < right) {
            long med = (left + right) / 2;
            long v = (med / w) * (med / h);

            if (v >= n) {
                right = med;
            } else {
                left = med + 1;
            }
        }

        output.println(left);
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner(new BufferedInputStream(System.in));
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
            solve(in, out);
        }
    }
}
