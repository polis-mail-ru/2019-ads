package ru.mail.polis.ads.part2.marashov.alexander;

import java.io.*;

public class Task2 {

    private Task2() {

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = in.nextInt();

        int[] numbers = new int[n + 2];

        for (int i = 1; i <= n; ++i) {
            numbers[i] = in.nextInt();
        }

        final int k = in.nextInt();
        int[] dynamics = new int[n + 2];

        for (int i = 1; i <= n + 1; ++i) {
            dynamics[i] = dynamics[i - 1];
            for (int step = 2; step <= k && i - step >= 0; ++step) {
                dynamics[i] = Math.max(dynamics[i], dynamics[i - step]);
            }
            dynamics[i] += numbers[i];
        }

        out.println(dynamics[n + 1]);

    }

    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

}
