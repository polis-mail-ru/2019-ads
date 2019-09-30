package ru.mail.polis.ads.part1;

import java.util.Arrays;
import java.util.Scanner;

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

    private static int getMaxSubsequence(final int i, final int j) {
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

    private static void solve() {
        final Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        arr1 = new int[n];
        for (int i = 0; i < n; i++) {
            arr1[i] = sc.nextInt();
        }
        final int m = sc.nextInt();
        arr2 = new int[m];
        for (int i = 0; i < m; i++) {
            arr2[i] = sc.nextInt();
        }
        matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(matrix[i], -1);
        }
        System.out.println(getMaxSubsequence(n -1, m - 1));
    }

    public static void main(final String[] arg) {
        solve();
    }
}
