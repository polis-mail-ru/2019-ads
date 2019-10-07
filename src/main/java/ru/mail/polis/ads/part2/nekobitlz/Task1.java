package ru.mail.polis.ads.part2.nekobitlz;

import java.io.PrintWriter;
import java.util.Scanner;

public class Task1 {
    public static void main(final String[] arg) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();

        int m = in.nextInt();
        int n = in.nextInt();

        int max = 100;
        int[][] dp = new int[max][max];

        for (int i = m - 1; i >= 0; i--)
            for (int j = 0; j < n; j++)
                dp[i][j] = in.nextInt();

        for (int i = 1; i < m; i++)
            dp[i][0] = dp[i][0] + dp[i - 1][0];

        for (int j = 1; j < n; j++)
            dp[0][j] = dp[0][j] + dp[0][j - 1];

        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                dp[i][j] = dp[i][j] + Math.max(dp[i - 1][j], dp[i][j - 1]);

        int k = m - 1;
        int t = n - 1;

        while (k > 0 || t > 0) {
            if (k > 0 && t > 0) {
                if (dp[k - 1][t] > dp[k][t - 1]) {
                    sb.append("F");
                    k--;
                } else {
                    sb.append("R");
                    t--;
                }
            } else if (k == 0) {
                sb.append("R");
                t--;
            } else if (t == 0) {
                sb.append("F");
                k--;
            }
        }

        String reverse = sb.reverse().toString();
        out.println(reverse);
        out.close();
    }
}