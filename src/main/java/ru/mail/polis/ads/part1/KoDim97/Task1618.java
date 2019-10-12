package ru.mail.polis.ads.part1.KoDim97;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task1618 {
    private Task1618() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int[] a = new int[m];
        for (int i = 0; i < m; i++) {
            a[i] = in.nextInt();
        }
        int n = in.nextInt();
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = in.nextInt();
        }
        int [][] c = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if (a[i - 1] == b[j - 1]){
                    c[i][j] = c[i - 1][j - 1] + 1;
                }
                else if (c[i-1][j] >= c[i][j-1]){
                    c[i][j] = c[i-1][j];
                }
                else {
                    c[i][j] = c[i][j-1];
                }
            }
        }
        out.print(c[m][n]);
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
