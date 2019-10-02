package ru.mail.polis.ads.part1.blinkyz;

import java.util.Scanner;

public class Problem1090 {
    private Problem1090() {

    }

    private static void solve() {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.nextLine();
        final int n = s.length();
        if (n == 0) {
            System.out.println();
            return;
        }

        final int[][] d = new int[n][n];
        final int[][] split = new int[n][n];
        for (int j = 0; j < n; j++) {
            for (int i = j; i >= 0; i--) {
                if (i == j) {
                    d[i][j] = 1;
                    continue;
                }
                int min = Integer.MAX_VALUE;
                int splitMin = -1;
                if (s.charAt(i) == '(' && s.charAt(j) == ')' ||
                        s.charAt(i) == '[' && s.charAt(j) == ']') {
                    min = d[i + 1][j - 1];
                }
                for (int k = i; k < j; k++) {
                    int curSplitCost = d[i][k] + d[k + 1][j];
                    if (curSplitCost < min) {
                        min = curSplitCost;
                        splitMin = k;
                    }
                }
                d[i][j] = min;
                split[i][j] = splitMin;
            }
        }
        restore(0, n - 1, s, d, split);
    }

    private static void restore(int i, int j,
                                String s,
                                int[][] d, int[][] split) {
        if (i == j) {
            switch (s.charAt(i)) {
                case '(':
                case ')': {
                    System.out.print("()");
                    break;
                }
                case '[':
                case ']': {
                    System.out.print("[]");
                    break;
                }
            }
            return;
        }

        if (d[i][j] == 0) {
            System.out.print(s.substring(i, j + 1));
            return;
        }

        final int k = split[i][j];
        if (k == -1) {
            System.out.print(s.charAt(i));
            restore(i + 1, j - 1, s, d, split);
            System.out.print(s.charAt(j));
            return;
        }

        restore(i, k, s, d, split);
        restore(k + 1, j, s, d, split);
    }

    public static void main(final String[] arg) {
        solve();
    }
}
