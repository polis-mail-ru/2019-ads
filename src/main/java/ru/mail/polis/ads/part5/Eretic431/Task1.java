package main.java.ru.mail.polis.ads.part5.Eretic431;

import java.util.Scanner;

/**
 * https://www.e-olymp.com/ru/submissions/5923939
 */

public class Task1 {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final double c = in.nextDouble();

        double l = 0;
        double r = c;
        double x;
        double y;

        do {
            x = (l + r) / 2;
            y = x * x + Math.sqrt(x);

            if (y > c) {
                r = x;
            } else {
                l = x;
            }
        } while (Math.abs(y - c) > 1e-6);

        System.out.println(String.format("%.6f", x));
    }
}
