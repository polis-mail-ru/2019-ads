package ru.mail.polis.ads.part2.marashov.alexander;

import java.io.*;

public class Task1 {

    private Task1() {

    }

    private static char[][] way;

    private static void printWay(final PrintWriter out, final int lowerIndex,  final int mIndex, final int nIndex) {
        final boolean isLowerCage = mIndex == lowerIndex;
        final boolean isLefterCage = nIndex == 1;
        if (isLowerCage && isLefterCage) {
            return;
        }

        if (way[mIndex][nIndex] == 'F') {
            printWay(out, lowerIndex, mIndex + 1, nIndex);
        } else {
            printWay(out, lowerIndex, mIndex, nIndex - 1);
        }
        out.print(way[mIndex][nIndex]);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int m = in.nextInt();
        final int n = in.nextInt();

        int[][] grains = new int[m][n];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                grains[i][j] = in.nextInt();
            }
        }

        int[][] dynamics = new int[m + 1][n + 1];
        way = new char[m + 1][n + 1];

        for (int i = m - 1; i >= 0; --i) {
            for (int j = 1; j <= n; ++j) {
                if (dynamics[i + 1][j] > dynamics[i][j - 1] || way[i][j - 1] == 0) {
                    dynamics[i][j] = dynamics[i + 1][j];
                    way[i][j] = 'F';
                } else {
                    dynamics[i][j] = dynamics[i][j - 1];
                    way[i][j] = 'R';
                }
                dynamics[i][j] += grains[i][j - 1];
            }
        }

        printWay(out, m - 1, 0, n);
    }

    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

}
