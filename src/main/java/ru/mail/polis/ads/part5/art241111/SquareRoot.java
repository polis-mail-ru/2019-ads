package ru.mail.polis.ads.part5.art241111;

import java.util.Scanner;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.StrictMath.sqrt;

public class SquareRoot {

    private static double check(double x) {
        return pow(x, 2) + sqrt(x);
    }

    private static double binSearch(double c) {
        double L = 0, R = 1e10, M;
        while(abs(R - L) > 1e-7) {
            M = (L + R) / 2;
            if(check(M) - c < 0) {
                L = M;
            }
            else {
                R = M;
            }
        }
        return R;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        double c = in.nextDouble();
        double result = binSearch(c);

        System.out.printf("%.6f",result);

    }
}
