package ru.mail.polis.ads.part1.nik27090;

import java.io.PrintWriter;
import java.util.Scanner;

/*
Задача: НОП
Решение: https://www.e-olymp.com/ru/submissions/5769040
 */


public final class Hoc {

    private Hoc(){

    }

    public static void main(final String[] args) {
        final Scanner con = new Scanner(System.in);
        int n = con.nextInt();
        int x[] = new int[n+1];
        for(int i = 1; i <= n; i++){
            x[i] = con.nextInt();
        }
        int m = con.nextInt();
        int y[] = new int[m+1];
        for(int i = 1; i <= m; i++){
            y[i] = con.nextInt();
        }
        int dp[][] = new int[2][m+1];

        for(int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (x[i] == y[j]) {
                    dp[i % 2][j] = 1 + dp[(i - 1) % 2][j - 1];
                } else {
                    dp[i % 2][j] = Math.max(dp[(i - 1) % 2][j], dp[i % 2][j - 1]);
                }
            }
        }
        try (PrintWriter out = new PrintWriter(System.out)){
        out.println(dp[n%2][m]);}
    }
}

