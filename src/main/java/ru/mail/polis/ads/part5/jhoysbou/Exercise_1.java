package ru.mail.polis.ads.part5.jhoysbou;

import java.util.Scanner;

// Submission here https://www.e-olymp.com/ru/submissions/5957507

public class Exercise_1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double constant = scanner.nextDouble();

        System.out.println(solve(constant));

    }

    private static double solve (double constant) {
        double low = 0, high = constant;
        double mid = (low + high) / 2;

        while (Math.abs(getValue(mid) - constant) > 0.000001) {
            if (getValue(mid)  < constant) {
                low = mid;
            }
            else if (getValue(mid)  > constant) {
                high = mid;
            }
            mid = (low + high) / 2;
        }
        return mid;
    }

    private static double getValue(double argument) {
        return Math.abs(Math.pow(argument, 2) + Math.sqrt(argument));
    }
}
