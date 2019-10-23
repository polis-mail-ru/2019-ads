package ru.mail.polis.ads.part5.bardaev;

import java.util.Scanner;

public class Task3968 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double c = in.nextDouble();

        double l = 0;
        double r = c;

        double x, y;
        do {
            x = (l + r) / 2;
            y = f(x);
            if (y > c) {
                r = x;
            } else {
                l = x;
            }
        } while (Math.abs(y-c)>=1e-6);
        System.out.println(String.format("%.6f", x));
    }

    public static double f(double x) {
        return x*x+Math.sqrt(x);
    }
}
