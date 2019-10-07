package ru.mail.polis.ads.part1.kokobox7;

import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
    Скобочная последовательность
    e-olymp submission: https://www.e-olymp.com/ru/submissions/5788824
 */

public final class Task2 {

    private static void solve(final Scanner in, final PrintWriter out) {
        String str = in.nextLine();
        if (str.isEmpty()) {
            out.println();
            return;
        }
        int len = str.length();
        int[][] dp = new int[len][len];
        int[][] split = new int[len][len];

        for (int j = 0; j < len; j++) {
            for (int i = j; i >= 0; i--) {
                if (i == j) {
                    dp[i][j] = 1;
                    continue;
                }
                int min = Integer.MAX_VALUE;
                int splitMin = -1;
                if ((str.charAt(i) == '(' && str.charAt(j) == ')')
                        || (str.charAt(i) == '[' && str.charAt(j) == ']')) {
                    min = dp[i + 1][j - 1];
                }
                for (int kSplit = i; kSplit < j; kSplit++) {
                    if (dp[i][kSplit] + dp[kSplit + 1][j] < min) {
                        min = dp[i][kSplit] + dp[kSplit + 1][j];
                        splitMin = kSplit;
                    }
                }
                dp[i][j] = min;
                split[i][j] = splitMin;
            }
        }
        //System.out.println(d[0][n - 1]);
        restore(0, len - 1, str, dp, split);
    }

    private static void restore(int i, int j,
                                String str,
                                int[][] dp, int[][] split) {
        if (i == j) {
            switch (str.charAt(i)) {
                case '(':
                case ')':
                    System.out.print("()");
                    break;
                case ']':
                case '[':
                    System.out.print("[]");
                    break;
                default:
                    throw new InputMismatchException("Wrong symbol");
            }
            return;
        }
        if (dp[i][j] == 0) {
            System.out.print(str.substring(i, j + 1));
            return;
        }
        if (split[i][j] == -1) {
            System.out.print(str.charAt(i));
            restore(i + 1, j - 1, str, dp, split);
            System.out.print(str.charAt(j));
            return;
        }
        int kSplit = split[i][j];
        restore(i, kSplit, str, dp, split);
        restore(kSplit + 1, j, str, dp, split);
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
