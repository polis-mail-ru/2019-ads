package ru.mail.polis.ads.part2;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task2 {

    private static int[] d;
    private static int step;

    private Task2() {
        // Should not be instantiated
    }

    private static void findMax(int i) {
        int max = -1001;
        int start = Math.max(i - step, 0);
        for (int j = start; j < i; ++j) {
            if (d[j] > max) {
                max = d[j];
            }
        }
        d[i] += max;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        d = new int[n + 3];

        d[0] = 0;
        d[n + 1] = 0;

        for (int i = 1; i < n + 1; i++) {
            d[i] = in.nextInt();
        }

        step = in.nextInt();

        int i, j = 0, max;

        for (i = 1; i <= n + 1; i++) {
            max = d[j];
            for (j = i - step; j < i; j++) {
                if (j < 0) j = 0;
                if (max < d[j]) max = d[j];
            }
            d[i] = max + d[i];
        }
        System.out.println(d[n + 1]);
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
