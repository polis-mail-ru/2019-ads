package ru.mail.polis.ads.part1.vegtamr-ab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task4 {
    
    private static void solve(final FastScanner in, final PrintWriter out) {
        int nFirst = in.nextInt();
        int[] arrFirst = new int[nFirst];
        for (int i = 0; i < nFirst; ++i) {
            arrFirst[i] = in.nextInt();
        }

        int nSecond = in.nextInt();
        int[] arrSecond = new int[nSecond];
        for (int i = 0; i < nSecond; ++i) {
            arrSecond[i] = in.nextInt();
        }

        int[][] matrix = new int[nFirst + 1][nSecond + 1];
        for (int i = 1; i <= nFirst; ++i) {
            for (int j = 1; j <= nSecond; ++j) {
                if (arrFirst[i - 1] == arrSecond[j - 1]) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i][j - 1], matrix[i - 1][j]);
                }
            }
        }

        out.println(matrix[nFirst][nSecond]);
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
