package ru.mail.polis.ads.part1.Marashov_Alexander;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public final class Task4 {
    private Task4() {

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] sequence1 = new int[n];
        for (int i = 0; i < n; ++i) {
            sequence1[i] = in.nextInt();
        }

        int m = in.nextInt();
        int[] sequence2 = new int[m];
        for (int i = 0; i < m; ++i) {
            sequence2[i] = in.nextInt();
        }

        final int len1 = sequence1.length;
        final int len2 = sequence2.length;
        int[][] lengths = new int[2][len2];
        for (int i = 0; i < len1; ++i) {
            lengths[i % 2][0] = (sequence1[i] == sequence2[0]) ? 1 : 0;
            for (int j = 1; j < len2; ++j) {
                if (sequence1[i] == sequence2[j]) {
                    lengths[i % 2][j] = lengths[(i + 1) % 2][j - 1] + 1;
                } else {
                    lengths[i % 2][j] = Math.max(lengths[(i + 1) % 2][j], lengths[(i % 2)][j - 1]);
                }
            }
        }

        out.println(lengths[(len1 - 1) % 2][len2 - 1]);
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
