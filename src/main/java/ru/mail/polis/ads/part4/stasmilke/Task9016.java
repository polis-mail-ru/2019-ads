package ru.mail.polis.ads.part4.stasmilke;

import java.io.*;
import java.util.StringTokenizer;


public class Task9016 {
    private Task9016() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        long[] array = new long[n + 1];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextLong();
        }
        for (int i = 0; i < q; i++) {
            out.println(isHeap(array, 0, array.length - 1, in.nextLong()) ? "YES" : "NO");
        }
    }

    private static boolean isHeap(long[] array, int begin, int end, long x) {
        if (begin >= end) {
            return x == array[begin];
        }
        int mid = begin + (end - begin) / 2;
        if (x == array[mid]) {
            return true;
        } else if (x < array[mid]) {
            return isHeap(array, begin, mid - 1, x);
        } else {
            return isHeap(array, mid + 1, end, x);
        }
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
