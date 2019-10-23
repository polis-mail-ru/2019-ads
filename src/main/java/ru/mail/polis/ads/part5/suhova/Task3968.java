package ru.mail.polis.ads.part5.suhova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task3968 {
    /*
    Task 1: https://www.e-olymp.com/ru/submissions/5924025
     */
    private static void solve() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        double c = Double.parseDouble(in.readLine());
        double l = 0;
        double r = c;
        double e = 0.000001;

        double x, y;
        do {
            x = (l + r) / 2;
            y = f(x);
            if (y > c) {
                r = x;
            } else l = x;
        } while (Math.abs(y - c) > e);

        System.out.print(String.format("%.6f", x));
    }

    private static double f(double x) {
        return x * x + Math.sqrt(x);
    }

    public static void main(final String[] arg) throws IOException {
        solve();
    }
}
