package ru.mail.polis.ads.part5.Kopeyka885;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task3969 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        long width = in.nextLong();
        long height = in.nextLong();
        long amount = in.nextLong();
        long left = Math.max(width, height);
        long right = amount * left;
        long m;
        long v;
        while (left < right) {
            m = (left + right) / 2;
            v = (m / width) * (m / height);
            if (v >= amount) {
                right = m;
            } else {
                left = m + 1;
            }
        }
        out.println(right);
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
        long nextLong(){
            return Long.parseLong(next());
        }

    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}