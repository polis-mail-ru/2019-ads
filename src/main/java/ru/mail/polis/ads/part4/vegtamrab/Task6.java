package ru.mail.polis.ads.part4.vegtamrab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task6 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        long[] array = new long[n + 1];

        for (int i = 0; i < n; ++i) {
            array[i] = in.nextLong();
        }

        for (int i = 0; i < q; ++i) {
            out.println(contains(array, 0, array.length - 1, in.nextLong()) ? "YES" : "NO");
        } 
    }

    private static boolean contains(long[] array, int begin, int end, long x) {
        if (begin >= end) {
            return x == array[begin];
        }

        int mid = begin + (end - begin) / 2;

        if (x == array[mid]) {
            return true;
        } else if (x < array[mid]) {
            return contains(array, begin, mid - 1, x);
        } else {
            return contains(array, mid + 1, end, x);
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
            System.out.println();
        }
    }
}
