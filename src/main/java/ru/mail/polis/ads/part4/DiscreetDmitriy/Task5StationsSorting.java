package ru.mail.polis.ads.part4.DiscreetDmitriy;

import java.io.*;
import java.util.StringTokenizer;

public class Task5StationsSorting {
    private Task5StationsSorting() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int max = Integer.MIN_VALUE;
        int i = n;

        while (i-- != 0) {
            int value = in.nextInt();

            if (value < max && value + max > m) {
                out.println("No");
                return;
            }

            max = Math.max(value, max);
        }

        out.println("Yes");
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