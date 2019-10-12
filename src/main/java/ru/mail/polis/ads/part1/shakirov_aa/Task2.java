package ru.mail.polis.ads.part1.shakirov_aa;

import java.io.IOException;
import java.util.Scanner;

public class BracketSequence {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
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

                if ((s.charAt(i) == '(' && s.charAt(j) == ')') || (s.charAt(i) == '[' && s.charAt(j) == ']')) {
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

        restore(0, n - 1, s, d, split);
    }

    public static void restore(int i, int j, String s, int[][] d, int[][] split) {
        if (i > j) {
            return;
        }

        if (i == j) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                System.out.print("()");
            } else {
                System.out.print("[]");
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
}