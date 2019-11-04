package ru.mail.polis.ads.part5.vegtamrab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task3 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        int[] d = new int[n];
        int max = 1;

        for (int i = 0; i < n; ++i) {
            a[i] = in.nextInt();
        }

        d[0] = 1;
        for (int i = 1; i < n; ++i) {
            int localMax = 0;

            for (int j = 0; j < i; ++j) {
                if ((a[j] != 0) && (a[i] % a[j] == 0) && d[j] > localMax) {
                    localMax = d[j];
                }
            }

            d[i] = localMax + 1;
            if (d[i] > max) {
                max = d[i];
            }
        }

        out.println(max);
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
