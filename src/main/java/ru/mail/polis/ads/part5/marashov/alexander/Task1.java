package ru.mail.polis.ads.part5.marashov.alexander;

import java.util.Scanner;

public class Task1 {

    private static double f(final double x) {
        return x * x + Math.sqrt(x);
    }

    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final double c = in.nextDouble();

        double left = 0;
        double right = c;
        final double epsilon = 0.000001;
        double center;
        double fCenter;
        do {
            center = (left + right) / 2;
            fCenter = f(center);
            if (fCenter > c) {
                right = center;
            } else {
                left = center;
            }
        } while (Math.abs(fCenter - c) > epsilon);
        System.out.println(center);
    }
}
