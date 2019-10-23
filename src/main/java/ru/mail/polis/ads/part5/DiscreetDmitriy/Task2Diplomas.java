package ru.mail.polis.ads.part5.DiscreetDmitriy;

import java.io.*;
import java.util.StringTokenizer;

public class Task2Diplomas {
    private Task2Diplomas() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        long w = in.nextInt();
        long h = in.nextInt();
        long n = in.nextInt();

        long l = Math.max(h, w);
        long r = n * l;

        while (l < r) {
        long m = (l + r) / 2;
        long v = (m / w) * (m / h);

            if (v >= n)
                r = m;
            else
                l = m + 1;
        }
        out.println(l);
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

