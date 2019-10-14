package ru.mail.polis.ads.part2.vegtamrab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task1 {
    
    private static void solve(final FastScanner in, final PrintWriter out) {
        StringBuilder builder = new StringBuilder();
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] field = new int[m][n];
        int[][] matrix = new int[m + 1][n + 1];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                field[i][j] = in.nextInt();
            }
        }

        for (int i = m - 1; i >= 0; --i) {
            for (int j = 1; j <= n; ++j) {
                matrix[i][j] = Math.max(matrix[i][j - 1], matrix[i + 1][j]) + field[i][j - 1];
            }
        }

        int i = 0;
        int j = n;

        while (i < m - 1 || j > 1) {
            if (i < m - 1 && j > 1) {
                if (matrix[i][j - 1] < matrix[i + 1][j]) {
                    builder.append('F');
                    ++i;
                } else {
                    builder.append('R');
                    --j;
                }
            } else if (i == m - 1) {
                builder.append('R');
                --j;
            } else if (j == 1) {
                builder.append('F');
                ++i;
            }

        }
        out.println(builder.reverse().toString());
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
            System.out.println();
        }
    }
}
