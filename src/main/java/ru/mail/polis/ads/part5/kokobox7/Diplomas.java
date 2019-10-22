package ru.mail.polis.ads.part5.kokobox7;

import java.io.*;
import java.util.StringTokenizer;

public class Diplomas {

    private static long w;
    private static long h;
    private static long n;

    private static void solve(final FastScanner in, final PrintWriter out) {
        w = Long.parseLong(in.next());
        h = Long.parseLong(in.next());
        n = Long.parseLong(in.next());

        long left = Math.max(w, h);
        long right = n * Math.max(w, h);

        out.print(binarySearch(left, right));
    }

    private static long nFittingDiplomas(long m) {
        long x = (m / w) * (m / h);
        return x;
    }

    private static long binarySearch(long left, long right) {
        long center = (right + left) / 2;
        long v = nFittingDiplomas(center);
        if (right == left) return center;
        else if (v >= n) {
            return binarySearch(left, center);
        } else return binarySearch(center + 1, right);
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
