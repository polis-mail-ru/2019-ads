package ru.mail.polis.ads.part1.bogdanmendli;

import java.io.PrintWriter;
import java.util.Scanner;

public final class FirstTask {

    private FirstTask() {
    }

    private static void solve() {
        final Scanner in = new Scanner(System.in);
        final PrintWriter out = new PrintWriter(System.out);
        final int result = in.nextInt();
        out.println(result / 10 + " " + result % 10);
    }

    public static void main(final String[] args) {
        solve();
    }
}
