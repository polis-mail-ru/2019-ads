package ru.mail.polis.ads.part2.stasmilke;

import java.io.*;
import java.util.Scanner;

public class Task15 {
    private Task15() {
    }

    private static void solve(final Scanner in, final PrintWriter out) {
        StringBuilder builder = new StringBuilder();
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] field = new int[m][n];
        int[][] d = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                field[i][j] = in.nextInt();
            }
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 1; j <= n; j++) {
                d[i][j] = Math.max(d[i][j - 1], d[i + 1][j]) + field[i][j - 1];
            }
        }
        int i = 0;
        int j = n;
        while (i < m - 1 || j > 1) {
            if (i < m - 1 && j > 1) {
                if (d[i][j - 1] < d[i + 1][j]) {
                    builder.append('F');
                    i++;
                } else {
                    builder.append('R');
                    j--;
                }
            } else if (i == m - 1) {
                builder.append('R');
                j--;
            } else if (j == 1) {
                builder.append('F');
                i++;
            }

        }
        out.println(builder.reverse().toString());
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
