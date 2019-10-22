package ru.mail.polis.ads.part5.Kungurov;

import java.io.*;
import java.util.Scanner;
/**
 * created by kirill kungurov on 22.10.19
 * https://www.e-olymp.com/ru/submissions/5924023
 */
public class SolveOfProblem3968 {

    private static void solve(final Scanner in, final PrintWriter out) {

        double C = in.nextDouble();
        double y, x, left = 0, right = C;
        do {
            x = (left + right) / 2;
            y = func(x);
            if (y > C) {
                right = x;
            } else {
                left = x;
            }
        } while (Math.abs(y - C) >= 1.e-6);
        out.print(String.format("%.6f",x));
    }

    private static double func(double x) {
        return x * x + Math.sqrt(x);
    }


    public static void main(final String[] arg) {
        Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
