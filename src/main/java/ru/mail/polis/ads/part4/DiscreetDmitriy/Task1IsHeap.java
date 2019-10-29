package ru.mail.polis.ads.part4.DiscreetDmitriy;

import java.io.*;
import java.util.StringTokenizer;

public class Task1IsHeap {
    private Task1IsHeap() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = (int) in.nextLong();
        long[] a = new long[n + 1];

        for (int i = 1; i <= n; i++)
            a[i] = in.nextLong();


        out.println(isHeap(n, a) ? "YES" : "NO");
    }

    private static boolean isHeap(int n, long[] a) {
        for (int i = 1; i < n / 2; i++)
            if (2 * i <= n && a[i] > a[2 * i] ||
                    2 * i + 1 <= n && a[i] > a[2 * i + 1]) return false;

        return true;
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
