package ru.mail.polis.ads.part5.DiscreetDmitriy;

import java.io.PrintWriter;
import java.util.Scanner;

public class Task1SquareRoot {
    private Task1SquareRoot() {
    }

    private static void solve(final Scanner in, final PrintWriter out) {
        double c = in.nextDouble();
        double left = 0;
        double right = c;
        double x, y;
        do {
            x = (left + right) / 2;
            y = f(x);

            if (y > c)
                right = x;
            else
                left = x;

        } while (Math.abs(y - c) >= 1e-6);

        out.println(String.format("%.6f", x));
    }

    private static double f(double x) {
        return x * x + Math.sqrt(x);
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}

