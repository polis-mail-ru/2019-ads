package ru.mail.polis.ads.part2.bogdanmendli;

import java.util.Scanner;

public class SecondTask {

    private static int[] points;
    private static int[] path;

    private static void solve(final Scanner in) {
        final int n = in.nextInt();
        initMas(n, in);
        final int k = in.nextInt();

        initFirstElementsOfPath(k);
        initOtherElementsOfPath(k, n);

        System.out.println(path[n + 1]);
    }

    private static void initMas(final int n, final Scanner in) {
        points = new int[n + 2];
        path = new int[n + 2];
        points[0] = 0;
        points[n + 1] = 0;
        path[0] = 0;
        for (int i = 1; i <= n; i++) {
            points[i] = in.nextInt();
        }
    }

    private static void initFirstElementsOfPath(final int k) {
        for (int i = 1; i <= k; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                if (path[j] > max) {
                    max = path[j];
                }
            }
            path[i] = max + points[i];
        }
    }

    private static void initOtherElementsOfPath(final int k, final int n) {
        for (int i = k + 1; i < n + 2; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i - k; j < i; j++) {
                if (path[j] > max) {
                    max = path[j];
                }
            }
            path[i] = max + points[i];
        }
    }

        public static void main(String[] args) {
        solve(new Scanner(System.in));
    }
}
