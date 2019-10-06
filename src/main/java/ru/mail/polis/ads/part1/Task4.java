package ru.mail.polis.ads.part1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Problem solution template.
 */
public final class Task4 {

    private Task4() {
        // Should not be instantiated
    }

    private static int[] x;
    private static int[] y;
    private static int[][] matrix;

    private static int getMaxSubSequenceLength(int m, int n) {
        if (m == 0 || n == 0) return 0;
        if (matrix[m][n] != -1) return matrix[m][n];
        if (x[m] == y[n])
            return matrix[m][n] = 1 + getMaxSubSequenceLength(m - 1, n - 1);
        else
            return matrix[m][n] = Math.max(getMaxSubSequenceLength(m, n - 1), getMaxSubSequenceLength(m - 1, n));
    }

    public static void main(String[] args) {
        Scanner con = new Scanner(System.in);
        int n = con.nextInt();
        x = new int[n +1];
        for(int i = 1; i <= n; i++)
            x[i] = con.nextInt();
        int m = con.nextInt();
        y = new int[m +1];

        for(int i = 1; i <= m; i++)
            y[i] = con.nextInt();
        matrix = new int[n +1][m +1];

        for(int i = 0; i <= n; i++)
            Arrays.fill(matrix[i], -1);

        int res = getMaxSubSequenceLength(n, m);
        System.out.println(res);
        con.close();
    }
}
