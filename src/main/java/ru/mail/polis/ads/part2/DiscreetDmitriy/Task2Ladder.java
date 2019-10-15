package ru.mail.polis.ads.part2.DiscreetDmitriy;

import java.io.PrintWriter;
import java.util.Scanner;

public class Task2Ladder {
    private Task2Ladder() {
    }

    private static void solve(final Scanner in, final PrintWriter out) {
        int n = Integer.parseInt(in.nextLine().trim());
        String[] steps = in.nextLine().trim().split("\\s+");
        int k = Integer.parseInt(in.nextLine().trim());

        int[] res = new int[n + 2];
        int[] dp = new int[n + 2];

        dp[0] = 0;
        dp[n + 1] = 0;

        for (int i = 0; i < n; i++)
            dp[i + 1] = Integer.parseInt(steps[i]);

        for (int i = 1; i <= n + 1; i++) {
            int max = dp[i - 1];

            for (int j = i - k; j < i; j++) {
                if (j < 0) continue;
                max = Math.max(dp[j], max);
            }

            dp[i] += max;
            res[i] = dp[i];
        }

        out.println(res[n + 1]);
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
