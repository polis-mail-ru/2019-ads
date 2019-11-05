package ru.mail.polis.ads.part5;

import java.io.*;

/**
 * Problem solution template.
 */
public final class Task1 {
    private Task1() {
        // Should not be instantiated
    }


    private static void solve(final BufferedReader in, final BufferedWriter out) throws IOException {
        double n = Double.parseDouble(in.readLine());
        double e = 0.000001;
        double left = 0;
        double right = n;
        double x = (left + right) / 2;
        double y = f(x);
        while (Math.abs(y - n) > e) {
            if (y > n) {
                right = x;
            } else{
                left = x;
            }
            x = (left + right) / 2;
            y = f(x);
        }
        System.out.println(String.format("%.6f", x));
    }

    private static double f(double x) {
        return x * x + Math.sqrt(x);
    }


    public static void main(final String[] arg) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out))) {
            solve(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}