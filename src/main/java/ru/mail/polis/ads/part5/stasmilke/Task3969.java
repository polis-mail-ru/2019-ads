package ru.mail.polis.ads.part5.stasmilke;

import java.io.*;
import java.util.StringTokenizer;

public class Task3969 {
    private Task3969() {

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int w = in.nextInt();
        int h = in.nextInt();
        int n = in.nextInt();

        long l = Math.max(w, h);
        long r = n * l;
        while (l < r) {
            long mid = l + (r - l) / 2;
            long v = (mid / w) * (mid / h);
            if (v >= n) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        out.println(l);
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}