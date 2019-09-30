package ru.mail.polis.ads.part1.blinkyz;

import java.io.PrintWriter;

public final class Problem1 {
    private Problem1() {

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int num = in.nextInt();
        out.println(num / 10 + " " + num % 10);
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
