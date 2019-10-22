package ru.mail.polis.ads.part5.nekobitlz;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class Task1 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        double c = in.nextDouble();
        double left = 0;
        double right = c;
        double x, y;

        do {
            x = (left + right) / 2;
            y = func(x);
            if (y > c) right = x;
            else left = x;
        } while (abs(y - c) >= 1e-6);

        out.println(String.format("%.6f", x));
    }

    private static double func(double x) {
        return x * x + sqrt(x);
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

        double nextDouble() { return Double.parseDouble(next()); }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
