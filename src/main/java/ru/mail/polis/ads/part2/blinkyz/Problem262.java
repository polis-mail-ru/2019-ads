package ru.mail.polis.ads.part2.blinkyz;

import java.util.Scanner;

public class Problem262 {
    private Problem262() {
    }

    private static void solve() {
        final Scanner in = new Scanner(System.in);
        final int n = in.nextInt();
        final int[] a = new int[n + 2];
        a[0] = 0;
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
        }
        a[n + 1] = 0;
        final int k = in.nextInt();

        final int[] d = new int[n + 2];
        d[0] = a[0];
        for (int i = 1; i < n + 2; i++) {
            d[i] = d[i - 1] + a[i];
            for (int j = i - 2; j >= i - k && j >= 0; j--) {
                final int curSum = d[j] + a[i];
                if (curSum > d[i]) {
                    d[i] = curSum;
                }
            }
        }
        System.out.println(d[n + 1]);
    }

    public static void main(String[] args) {
        solve();
    }
}
