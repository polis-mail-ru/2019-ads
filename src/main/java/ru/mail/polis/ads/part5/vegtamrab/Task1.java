package ru.mail.polis.ads.part5.vegtamrab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task1 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        double c = in.nextDouble();

        out.println(String.format("%.6f", calculateRoot(0, c, c, 0.000001)));
    }
    
    private static double calculateRoot(double begin, double end, double c, double epsilon) {
        double x = begin + (end - begin) / 2;
        double y = Math.pow(x, 2) + Math.sqrt(x);

        if (Math.abs(Math.pow(x, 2) + Math.sqrt(x) - c) <= epsilon ) {
            return x;
        } else if (y > c) {
            return calculateRoot(begin, x, c, epsilon);
        } else {
            return calculateRoot(x, end, c, epsilon);
        }
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
