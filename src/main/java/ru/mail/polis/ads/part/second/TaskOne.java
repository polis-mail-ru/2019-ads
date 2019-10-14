package ru.mail.polis.ads.part.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author v.ivlev
 * https://www.e-olymp.com/ru/submissions/5856048
 */
public class TaskOne {

    private static void solve(FastScanner in, final PrintWriter out) {
        int[][] d;
        char[][] navigate;
        int m = in.nextInt();
        int n = in.nextInt();
        d = new int[m + 1][n + 1];
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= n; i++) {
            d[0][i] = -1;
        }

        navigate = new char[m + 1][n + 1];
        for (int i = m; i > 0; i--) {
            for (int j = 1; j <= n; j++) {
                d[i][j] = in.nextInt();
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (d[i][j - 1] > d[i - 1][j]) {
                    d[i][j] += d[i][j - 1];
                    navigate[i][j] = 'R';
                } else {
                    navigate[i][j] = 'F';
                    d[i][j] += d[i - 1][j];
                }
            }
        }
        int i = m, j = n;
        while (i > 1 || j > 1) {
            result.insert(0, navigate[i][j]);
            if (navigate[i][j] == 'F') {
                i--;
            } else {
                j--;
            }
        }
        if (i == 1) {
            for (int x = 1; x < j; x++) {
                result.insert(0, "R");
            }
        } else {
            for (int x = 1; x < i; x++) {
                result.insert(0, "F");
            }
        }
        out.println(result);
    }

    public static void main(final String[] arg) {
        FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
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
    }
}
