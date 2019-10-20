package ru.mail.polis.ads.part4.kokobox7;

import java.io.*;
import java.util.StringTokenizer;

public class IsItAHeap {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        long[] array = new long[n + 1];
        for (int i = 1; i < n + 1; i++) {
            array[i] = Long.parseLong(in.next());
        }
        out.println(isHeap(array) ? "YES" : "NO");
    }

    private static boolean isHeap(long[] array) {
        for (int i = 1; i < array.length; i++) {
            if (2 * i < array.length) {
                if (array[i] > array[2 * i]) {
                    return false;
                }
            }
            if (2 * i + 1 < array.length) {
                if (array[i] > array[2 * i + 1]) {
                    return false;
                }
            }
        }
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
