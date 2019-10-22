package ru.mail.polis.ads.part5.marashov.alexander;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final double w = in.nextLong();
        final double h = in.nextLong();
        final double n = in.nextLong();

        double left = 0;
        double right = n * Math.max(w, h);
        do {
            double center = Math.floor((left + right) / 2);
            final double dipCount = Math.floor(center / w) * Math.floor(center / h);
            if (dipCount > n) {
                right = center;
            } else if (dipCount == n) {
                double answer = center;
                while (Math.floor(answer / w) * Math.floor(answer / h) == n) {
                    answer -= 1;
                }
                System.out.println(String.format("%.0f", answer + 1));
                return;
            } else {
                left = center;
            }
        } while (right - left > 1);
        System.out.println(String.format("%.0f", right));
    }
}
