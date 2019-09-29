package ru.mail.polis.ads.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  submission - https://www.e-olymp.com/ru/submissions/5737769
 */
public final class Problem4 {

    private static int[] arr1;
    private static int[] arr2;
    private static int[][] matrix;

    private Problem4() {
        // Should not be instantiated
    }



    private static int getMaxSubsequence(int i, int j) {
        if (i < 0 || j < 0) {
            return 0;
        }
        if (matrix[i][j] != -1) {
            return matrix[i][j];
        }
        if (arr1[i] == arr2[j]) {
            matrix[i][j] = 1 + getMaxSubsequence(i - 1, j - 1);
            return matrix[i][j];
        }
        matrix[i][j] = Math.max(getMaxSubsequence(i -1, j), getMaxSubsequence(i, j - 1));
        return matrix[i][j];
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        arr1 = new int[n];
        for (int i = 0; i < n; i++) {
            arr1[i] = in.nextInt();
        }
        int m = in.nextInt();
        arr2 = new int[m];
        for (int i = 0; i < m; i++) {
            arr2[i] = in.nextInt();
        }
        matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(matrix[i], -1);
        }
        System.out.println(getMaxSubsequence(n -1, m - 1));
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
