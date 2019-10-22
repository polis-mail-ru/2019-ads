package ru.mail.polis.ads.part5.shakirov_aa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task1 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        double c = fs.nextDouble();
        double l = 0;
        double r = c;
        double x, y;
        do {
            x = (l+r)/2;
            y = f(x);
            if (y > c) {
                r = x;
            } else {
                l = x;
            }
        } while (Math.abs(y-c) > Math.pow(10, -6));

        System.out.println(String.format("%.6f", x));
    }

    static double f(double x) {
        return x*x + Math.sqrt(x);
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
