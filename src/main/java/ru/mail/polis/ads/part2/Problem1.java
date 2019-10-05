package ru.mail.polis.ads.part2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * submission https://www.e-olymp.com/ru/submissions/5784372
 */

public class Problem1 {

    private static int m;
    private static int n;
    private static int[][] matrix;

    private Problem1() {
        // Should not be instantiated
    }

    private static void solve() {
        final Scanner sc = new Scanner(System.in);
        m = sc.nextInt() + 1;
        n = sc.nextInt() + 1;
        matrix = new int[m][n];

        readMatrix(sc);

        calculate();

        printRoute();
    }

    private static void printRoute() {
        final Deque<Character> stack = new ArrayDeque<>();
        int i = 0;
        int j = n - 1;
        while (i < m - 2 || j > 1) {
            if (j > 1 && matrix[i][j - 1] >= matrix[i + 1][j]) {
                stack.push('R');
                j--;
            } else {
                stack.push('F');
                i++;
            }
        }

        stack.forEach(System.out::print);
    }

    private static void calculate() {
        for (int i = m - 2; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] = matrix[i][j] + Math.max(matrix[i + 1][j], matrix[i][j - 1]);
            }
        }
    }

    private static void readMatrix(final Scanner sc) {
        for (int i = 0; i < m - 1; i++) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
    }

    public static void main(final String[] arg) {
        solve();
    }
}
