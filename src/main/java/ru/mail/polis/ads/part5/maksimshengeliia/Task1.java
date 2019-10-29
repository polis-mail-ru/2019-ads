package ru.mail.polis.ads.part5.maksimshengeliia;


import java.util.Scanner;

/*
*   https://www.e-olymp.com/ru/submissions/5972033
* */
public class Task1 {

    public static void main(String[] strings) {
        Scanner f = new Scanner(System.in);
        double c = f.nextDouble();
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
        } while (Math.abs(y - c) > 1e-6);

        System.out.println(String.format("%.6f", x));
    }

    private static double f(double x) {
        return x * x + Math.sqrt(x);
    }
}
