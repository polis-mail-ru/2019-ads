package ru.mail.polis.ads.part2.KateMoreva;

import java.io.*;
import java.util.Scanner;

//e-olymp problem 15 "Мышка и зернышки"

public class Task1 {
    private Task1(){
    }

    private static char[][] answer;

    private static void solve(final Scanner in, final PrintWriter out){
        int m = in.nextInt();
        int n = in.nextInt();

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                dp[i][j] = in.nextInt();
        int[][] dyn = new int[m + 1][n + 1];
        answer = new char[m + 1][n + 1];

        for (int i = m - 1; i >= 0; --i)
            for (int j = 1; j <= n; ++j) {
                if (dyn[i + 1][j] > dyn[i][j - 1] || answer[i][j - 1] == 0) {
                    dyn[i][j] = dyn[i + 1][j];
                    answer[i][j] = 'F';
                } else {
                    dyn[i][j] = dyn[i][j - 1];
                    answer[i][j] = 'R';
                }
                dyn[i][j] += dp[i][j - 1];
            }
        print(out, m - 1, 0, n);
    }

    private static void print(final PrintWriter out, final int lowerIndex, final int myIndex, final int nIndex){
        final boolean isLefterCage = (nIndex == 1);
        final boolean isLowerCage = (myIndex == lowerIndex);
        if (isLowerCage && isLefterCage) {
            return;
        }

        if (answer[myIndex][nIndex] == 'R') {
            print(out, lowerIndex, myIndex, nIndex - 1);
        } else {
            print(out, lowerIndex, myIndex + 1, nIndex);
        }
        out.print(answer[myIndex][nIndex]);
    }


    public static void main(final String[] arg){
        Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
