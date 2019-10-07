package ru.mail.polis.ads.part2.nekobitlz;

import java.io.PrintWriter;
import java.util.Scanner;

import static java.lang.Math.max;

public class Task2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(in.nextLine().trim());
        String[] steps = in.nextLine().trim().replaceAll("[\\s]{2,}", " ").split(" ");
        int k = Integer.parseInt(in.nextLine().trim());

        int[] res = new int[n + 2];
        int[] dp = new int[n + 2];
        int max;

        dp[0] = 0;
        dp[n + 1] = 0;

        for (int i = 0; i < n; i++) {
            dp[i + 1] = Integer.parseInt(steps[i]);
        }

        for (int i = 1; i <= n + 1; i++) {
            max = dp[i - 1];

            for (int j = i - k; j < i; j++) {
                if (j < 0) continue;
                max = max(dp[j], max);
            }

            dp[i] = max + dp[i];
            res[i] = dp[i];
        }

        out.println(res[n + 1]);
        out.close();
    }
}