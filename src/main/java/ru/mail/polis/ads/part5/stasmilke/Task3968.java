package ru.mail.polis.ads.part5.stasmilke;

import java.io.*;
import java.util.StringTokenizer;


public class Task3968 {
    private Task3968() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        double c = in.nextDouble();
        out.println(String.format("%.6f", findSolution(0, c, c, 0.000001)));
    }

    private static double findSolution(double begin, double end, double c, double epsilon) {
        double x = begin + (end - begin) / 2;
        double y = function(x);
        if (Math.abs(function(x) - c) <= epsilon ) {
            return x;
        } else if (y > c) {
            return findSolution(begin, x, c, epsilon);
        } else {
            return findSolution(x, end, c, epsilon);
        }
    }

    private static double function(double x) {
        return Math.pow(x, 2) + Math.sqrt(x);
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

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}