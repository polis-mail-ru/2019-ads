package ru.mail.polis.ads.part5.bogdanmendli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FirstTask {

    private static double c;

    private static void solve() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        c = Double.parseDouble(in.readLine());
        double left = 0;
        double right = c;
        double x, y;
        do {
            x = (left + right) / 2;
            y = f(x);
            if (y > c) {
                right = x;
            } else {
                left = x;
            }
        } while (Math.abs(y - c) >= 0.000001);
        System.out.println(x);
    }

    private static double f(double x) {
        return x * x + Math.sqrt(x);
    }

    public static void main(String[] args) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
