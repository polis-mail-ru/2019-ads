package ru.mail.polis.ads.part1.Kokobox7;

import java.io.PrintWriter;
import java.util.Scanner;

/*
    Скобочная последовательность
    e-olymp submission: https://www.e-olymp.com/ru/submissions/5788824
 */

public final class Task2 {

    private static void solve(final Scanner in, final PrintWriter out) {
        String s = in.nextLine();
        if (s.isEmpty()) {
            out.println();
            return;
        }
        int n = s.length();
        int[][] d = new int[n][n];
        int[][] split = new int[n][n];

        for (int j = 0; j < n; j++) {
            for (int i = j; i >= 0; i--) {
                if (i == j) {
                    d[i][j] = 1;
                    continue;
                }
                int min = Integer.MAX_VALUE;
                int splitMin = -1;
                if ((s.charAt(i) == '(' && s.charAt(j) == ')') ||
                        (s.charAt(i) == '[' && s.charAt(j) == ']')) {
                    min = d[i + 1][j - 1];
                }
                for (int k = i; k < j; k++) {
                    if (d[i][k] + d[k + 1][j] < min) {
                        min = d[i][k] + d[k + 1][j];
                        splitMin = k;
                    }
                }
                d[i][j] = min;
                split[i][j] = splitMin;
            }
        }
        //System.out.println(d[0][n - 1]);
        restore(0, n - 1, s, d, split);
    }

    static private void restore(int i, int j,
                                String s,
                                int[][] d, int[][] split) {
        if (i == j) {
            switch (s.charAt(i)) {
                case '(':
                case ')':
                    System.out.print("()");
                    break;
                case ']':
                case '[':
                    System.out.print("[]");
                    break;
                default:
                    throw new RuntimeException("Wrong symbol");
            }
            return;
        }
        if (d[i][j] == 0) {
            System.out.print(s.substring(i, j + 1));
            return;
        }
        if (split[i][j] == -1) {
            System.out.print(s.charAt(i));
            restore(i + 1, j - 1, s, d, split);
            System.out.print(s.charAt(j));
            return;
        }
        int k = split[i][j];
        restore(i, k, s, d, split);
        restore(k + 1, j, s, d, split);
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
