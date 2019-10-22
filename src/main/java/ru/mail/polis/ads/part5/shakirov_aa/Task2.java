package ru.mail.polis.ads.part5.shakirov_aa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task2 {
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        long w = in.nextInt();
        long h = in.nextInt();
        long n = in.nextInt();

        long l = Math.max(w, h);
        long r = n * l;

        while (l < r) {
            long m = (l + r) / 2;
            long v = (m/w) * (m/h);
            if (n <= v) {
                r = m;
            } else {
                l = m + 1;
            }
        }

        System.out.println(l);
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
}
