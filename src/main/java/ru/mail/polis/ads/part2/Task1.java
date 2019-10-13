package ru.mail.polis.ads.part2;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task1 {

    private static int[][] d;

    private Task1() {
        // Should not be instantiated
    }

    private static void answer(int i, int j, int m, int n) {
        if (i == m && j == 1) {
            return;
        } else {
            if (d[i][j - 1] >= d[i + 1][j] && j - 1 != 0) {
                answer(i, j - 1, m, n);
                System.out.print("R");
            } else if (i + 1 != m + 1) {
                answer(i + 1, j, m, n);
                System.out.print("F");
            } else if (j - 1 == 0) {
                answer(i + 1, j, m, n);
            } else {
                answer(i, j - 1, m, n);
            }
        }

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        //Scanner sc = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        d = new int[m + 2][n + 2];

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                d[i][j] = in.nextInt();
            }
        }

        for (int i = m; i >= 1; --i) {
            for (int j = 1; j <=  n; ++j) {
                d[i][j] += Math.max(d[i][j - 1], d[i + 1][j]);
            }
        }

        answer(1, n, m, n);

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
