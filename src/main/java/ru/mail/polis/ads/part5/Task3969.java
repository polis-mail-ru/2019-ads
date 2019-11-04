package ru.mail.polis.ads.part5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task3969 {
    private Task3969() {
        // Should not be instantiated https://www.e-olymp.com/ru/submissions/6013833
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        long width = in.nextInt();
        long height = in.nextInt();
        long count = in.nextInt();
        long maxDim = Math.max(width, height);
        long maxLen = count * maxDim;
        while (maxDim < maxLen) {
            long median = (maxDim + maxLen) / 2;
            long num = (median/width) * (median/height);
            if (count <= num) {
                maxLen = median;
            } else {
                maxDim = median + 1;
            }
        }
        out.println(maxDim);
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
