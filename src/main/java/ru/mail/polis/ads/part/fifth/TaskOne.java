package ru.mail.polis.ads.part.fifth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author v.ivlev
 * https://www.e-olymp.com/ru/submissions/6028837
 */
public class TaskOne {

    private static void solve(FastScanner in, final PrintWriter out) {
        double value = Double.parseDouble(in.nextLine());
        double left = 0;
        double right = value;
        double e = 0.000001;
        double x;
        double y;

        do {
            x = (left + right) / 2;
            y = x * x + Math.sqrt(x);
            if (y > value) {
                right = x;
            } else {
                left = x;
            }
        } while (Math.abs(y - value) > e);

        out.print(String.format("%.6f", x));
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

        String nextLine() {
            return next();
        }
    }

    public static void main(final String[] arg) {
        FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
