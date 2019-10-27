package ru.mail.polis.ads.part5;

import java.util.Scanner;

/**
 * submission - https://www.e-olymp.com/ru/submissions/5923884
 */
public class Problem1 {

    private static void solve() {
        double c = new Scanner(System.in).nextDouble();
        double left = 0;
        double right = c;
        while (true) {
            double x = (left + right) / 2;
            double y = x*x + Math.sqrt(x);
            if (Math.abs(y - c) < 0.0000001) {
                System.out.println(x);
                break;
            }
            if (y < c) {
                left = x;
            } else {
                right = x;
            }
        }
    }

    public static void main(String[] args) {
        solve();
    }
}
