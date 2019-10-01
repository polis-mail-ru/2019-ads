package ru.mail.polis.ads.part1.suhova;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Task1618 {
    /*
    https://www.e-olymp.com/ru/submissions/5735270
     */
    private static void solve(final Task1618.FastScanner in, final PrintWriter out) {
        int n1 = in.nextInt();
        int[] arr1 = new int[n1];
        for (int i = 0; i < n1; i++) {
            arr1[i] = in.nextInt();
        }
        int n2 = in.nextInt();
        int[] arr2 = new int[n2];
        for (int i = 0; i < n2; i++) {
            arr2[i] = in.nextInt();
        }
        int[][] map = new int[n1][n2];
        for (int i = 0; i < n1; i++) {
            Arrays.fill(map[i], -1);
        }
        int count = maxSub(n1 - 1, n2 - 1, arr1, arr2, map);
        out.println(count);
        out.flush();
    }

    public static int maxSub(int i, int j, int[] a, int[] b, int[][] map) {
        if (i == -1 || j == -1)
            return 0;
        if (a[i] == b[j]) {
            if (i == 0 || j == 0) return maxSub(i - 1, j - 1, a, b, map) + 1;
            if (map[i - 1][j - 1] == -1) map[i - 1][j - 1] = maxSub(i - 1, j - 1, a, b, map);
            return map[i - 1][j - 1] + 1;
        }
        if (i == 0 || j == 0) return 0;
        if (map[i][j - 1] == -1) map[i][j - 1] = maxSub(i, j - 1, a, b, map);
        if (map[i - 1][j] == -1) map[i - 1][j] = maxSub(i - 1, j, a, b, map);
        return Math.max(map[i][j - 1], map[i - 1][j]);
    }

    public static void main(final String[] arg) {
        final Task1618.FastScanner in = new Task1618.FastScanner(System.in);
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
