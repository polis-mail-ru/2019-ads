package ru.mail.polis.ads.part4.nekobitlz;

import java.io.*;
import java.util.StringTokenizer;

public class Task1 {

    private Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = (int) in.nextLong();
        long[] a = new long[n + 1];

        for (int i = 1; i < n + 1; i++) {
            a[i] = in.nextLong();
        }

        for (int i = 1; i < n + 1; i++) {
            if ((2 * i < n + 1) && !(a[i] <= a[2 * i])) {
                out.println("NO");
                return;
            }

            if ((2 * i + 1 < n + 1) && !(a[i] <= a[2 * i + 1])) {
                out.println("NO");
                return;
            }
        }

        out.println("YES");
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
