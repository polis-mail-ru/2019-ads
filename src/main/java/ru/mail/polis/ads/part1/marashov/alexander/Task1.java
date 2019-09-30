package ru.mail.polis.ads.part1.marashov.alexander;

import java.io.PrintWriter;
import java.util.Scanner;

public final class Task1 {
    private Task1() {

    }

    private static void solve(final Scanner in, final PrintWriter out) {
        final int number = in.nextInt();
        out.println((number / 10) + " " + (number % 10));
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
