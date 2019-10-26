package ru.mail.polis.ads.lectionWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FirstInLection {
    public static void main(String[] args) throws IOException {
        double decimal;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        decimal = Double.parseDouble(in.readLine());
        double left = 0;
        double right = decimal;
        double x, y;
        do {
            x = (left + right) / 2;
            y = checkIt(x);
            if (y > decimal) {
                right = x;
            } else {
                left = x;
            }
        } while (Math.abs(y - decimal) > 1e-6);

        System.out.println(String.format("%.6f", x));
    }

    private static double checkIt(double x) {
        return x*x + Math.sqrt(x);
    }
}
