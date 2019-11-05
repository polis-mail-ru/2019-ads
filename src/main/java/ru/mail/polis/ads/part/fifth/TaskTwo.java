package ru.mail.polis.ads.part.fifth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author v.ivlev
 * https://www.e-olymp.com/ru/submissions/6028877
 */
public class TaskTwo {

    private static void solve(FastScanner in, final PrintWriter out) {
        long width = in.nextInt();
        long height = in.nextInt();
        long n = in.nextInt();
        long left = Math.max(width, height);
        long rigth = n * Math.max(width, height);
        long d;
        long m;
        do {
            d = (left + rigth) / 2;
            m = (d / height) * (d / width);
            if (m >= n) {
                rigth = d;
            } else {
                left = d + 1;
            }
        } while (left < rigth);
        out.print(left);
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
        FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
