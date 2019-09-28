package ru.mail.polis.ads.part1.bogdanMendli;

import java.io.PrintWriter;
import java.util.Scanner;

public final class FirstTask {

    private FirstTask() {
    }

    private static void solve(final Scanner in, final PrintWriter out) {
        final int result = in.nextInt();
        out.println(result / 10 + " " + result % 10);
    }

    public static void main(final String[] args) {
        try (PrintWriter out = new PrintWriter(System.out);
             Scanner in = new Scanner(System.in)) {
            solve(in, out);
        }
    }
}
