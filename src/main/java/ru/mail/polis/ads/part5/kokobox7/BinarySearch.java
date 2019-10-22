package ru.mail.polis.ads.part5.kokobox7;

import java.io.*;
import java.util.StringTokenizer;

public class BinarySearch {
    private static final double EPS = 1E-6;
    private static double c;

    private static void solve(final FastScanner in, final PrintWriter out) {
        c = Double.parseDouble(in.next());
        double left = 0;
        double right = c;

        out.printf("%.6f", binarySearch(left, right));
    }

    private static double y(double x) {
        return Math.pow(x, 2) + Math.sqrt(x);
    }

    private static double binarySearch(double left, double right) {
        double center = (right + left) / 2;
        double yAtCenter = y(center);
        if (Math.abs(yAtCenter - c) < EPS) {
            return center;
        } else if (yAtCenter > c) return binarySearch(left, center);
        else return binarySearch(center, right);
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
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
