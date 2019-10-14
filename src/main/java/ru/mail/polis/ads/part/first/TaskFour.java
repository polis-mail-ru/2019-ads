package ru.mail.polis.ads.part.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author v.ivlev
 * https://www.e-olymp.com/ru/submissions/5855302
 */
public class TaskFour {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] arrN = new int[n];
        for (int i = 0; i < n; i++) {
            arrN[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] arrM = new int[m];
        for (int i = 0; i < m; i++) {
            arrM[i] = in.nextInt();
        }
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(map[i], -1);
        }
        int count = maxSub(n - 1, m - 1, arrN, arrM, map);
        out.println(count);
    }

    public static int maxSub(int i, int j, int[] a, int[] b, int[][] map) {
        if (i == -1 || j == -1) {
            return 0;
        }
        if (a[i] == b[j]) {
            if (i == 0 || j == 0) return maxSub(i - 1, j - 1, a, b, map) + 1;
            if (map[i - 1][j - 1] == -1) map[i - 1][j - 1] = maxSub(i - 1, j - 1, a, b, map);
            return map[i - 1][j - 1] + 1;
        }
        if (i == 0 || j == 0) {
            return 0;
        }
        if (map[i][j - 1] == -1) {
            map[i][j - 1] = maxSub(i, j - 1, a, b, map);
        }
        if (map[i - 1][j] == -1) {
            map[i - 1][j] = maxSub(i - 1, j, a, b, map);
        }
        return Math.max(map[i][j - 1], map[i - 1][j]);
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
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
