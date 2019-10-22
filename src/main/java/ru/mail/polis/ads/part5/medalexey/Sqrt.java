package ru.mail.polis.ads.part5.medalexey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


/**
 *  Задача: Квадратный корень  https://www.e-olymp.com/ru/problems/3968
 *  Тестирование: https://www.e-olymp.com/ru/submissions/5923956
 */
public class Sqrt {

    private Sqrt() {
    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        double c = in.nextDouble();

        out.println(
                String.format("%.6f", findResult(0, c, c))
        );
    }


    private static double findResult(double left, double right, double c) {

        double median = (left+right)/2;

        while ( Math.abs(f(median) - c) > 1e-6 ) {
            if (f(median) > c) {
                right = median;
            } else {
                left = median;
            }
            median = (left+right)/2;
        }

        return median;
    }


    private static double f(double x) {
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


    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
